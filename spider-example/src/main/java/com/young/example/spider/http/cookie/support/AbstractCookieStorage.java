package com.young.example.spider.http.cookie.support;

import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.config.common.SpiderCookieLogin;
import com.young.example.spider.config.support.SpiderConfigException;
import com.young.example.spider.http.cookie.CommandRunner;
import com.young.example.spider.http.cookie.CookieException;
import com.young.example.spider.spider.thread.CrawlerThreadPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public abstract class AbstractCookieStorage {

    private static final Logger log = Logger.getLogger(AbstractCookieStorage.class);

    private static final CrawlerThreadPool threadPool = new CrawlerThreadPool();

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String RESULT_PNG = "_result.png";

    private static final String COOKIE_fILE = "_cookie.json";

    public abstract String readCookie(String storePath) throws CookieException;

    public abstract void writeBack(String storePath, String cookieJson) throws CookieException;

    public abstract void delCookie(String storePath) throws CookieException;

    protected String getCookie(SpiderCookieLogin login, boolean is_login) throws CookieException {
        String cookieQueueName = null;
        List<Map<String, Object>> cookieMap = new ArrayList<Map<String, Object>>();
        String cookieJson = null;
        try {
            cookieQueueName = ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix() + login.getQueue();
            cookieJson = readCookie(cookieQueueName);
            if (cookieJson == null && is_login) {
                cookieJson = login(login);
                if (!StringUtils.isBlank(cookieJson)) {
                    writeBack(cookieQueueName, cookieJson);
                }
            }
            if (cookieJson != null) {
                cookieMap = mapper.readValue(cookieJson, new TypeReference<List<Map<String, Object>>>() {
                });
            }
        } catch (Exception e) {
            throw new CookieException("get cookie error", e);
        }
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> map : cookieMap) {
            sb.append(map.get("name").toString() + "=" + map.get("value").toString() + ";");
        }
        return sb.toString();
    }

    protected void removeCookie(SpiderCookieLogin login) throws CookieException {
        String cookieQueueName = null;
        try {
            cookieQueueName = ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix() + login.getQueue();
            delCookie(cookieQueueName);
        } catch (Exception e) {
            throw new CookieException("del cookie error", e);
        }
    }

    private String getCommand(long timestamp, SpiderCookieLogin login) throws SpiderConfigException {
        String basePath = ConfigFactory.getConfig().getSpiderCookie().getTemppath();
        return "casperjs " + login.getScript() + " " + login.getUsername() + " " + " " + login.getPassword() + " " + basePath + File.separator + login.getType() + "_" + timestamp + "" + RESULT_PNG + " " + basePath + File.separator + login.getType() + "_" + timestamp + "" + COOKIE_fILE;
    }

    private String login(SpiderCookieLogin login) throws SpiderConfigException, InterruptedException, ExecutionException, TimeoutException, IOException {
        long now = System.currentTimeMillis();
        String basePath = ConfigFactory.getConfig().getSpiderCookie().getTemppath();
        String command = getCommand(now, login);
        Future<String> future = threadPool.submit(new CommandRunner(command));
        String result = future.get(3, TimeUnit.MINUTES);
        log.info("command execute result is -[" + result + "]");
        if (result.contains("successed")) {
            return FileUtils.readFileToString(new File(basePath + File.separator + login.getType() + "_" + now + "" + COOKIE_fILE));
        }
        return null;
    }
}
