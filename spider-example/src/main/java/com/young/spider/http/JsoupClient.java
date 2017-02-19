package com.young.spider.http;

import com.letv.sf.config.SpiderConfig;
import com.letv.sf.entity.common.CrawlerSlotConfig;
import com.letv.sf.entity.common.ProxyInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

/**
 * Created by yangyong3 on 2016/12/1.
 */
public class JsoupClient {

    private static final Logger log = LogManager.getLogger(JsoupClient.class);

    private String user_agent;

    private int timeout = 10000;

    private CrawlerSlotConfig slotConfig;

    private ProxyInfo proxy;

    public ProxyInfo getProxy() {
        return proxy;
    }

    public void setProxy(ProxyInfo proxy) {
        this.proxy = proxy;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    private Map<String, String> cookies;

    public JsoupClient(CrawlerSlotConfig slotConfig) {
        this.slotConfig = slotConfig;
        this.proxy = getProxy(slotConfig);
    }

    public JsoupClient(CrawlerSlotConfig slotConfig, Map<String, String> cookies) {
        this(slotConfig);
        this.cookies = cookies;
    }

    public JsoupClient(CrawlerSlotConfig slotConfig, Map<String, String> cookies, String user_agent) {
        this(slotConfig, cookies);
        this.user_agent = user_agent;
    }
    public JsoupClient() {
    }

    private static ProxyInfo getProxy(CrawlerSlotConfig config) {
        if (org.apache.commons.lang3.StringUtils.isBlank(config.getProxy_ip()))
            return null;
        ProxyInfo proxyInfo = new ProxyInfo();
        proxyInfo.setIp(config.getProxy_ip());
        proxyInfo.setPort(config.getProxy_port());
        proxyInfo.setUsername(config.getProxy_user());
        proxyInfo.setPassword(config.getProxy_pass());
        return proxyInfo;
    }

    private Connection getConnection(String url, Connection.Method method, Map<String, String> params) {
        Connection connection = HttpConnection.connect(url).timeout(timeout).ignoreContentType(true);
        if (!StringUtils.isBlank(user_agent)) {
            connection.userAgent(user_agent);
        }
        if (proxy != null) {
            connection.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.getIp(), proxy.getPort())));
            connection.credentials(proxy.getUsername(), proxy.getPassword());
        }
        if (cookies != null && cookies.size() > 0) {
            connection.cookies(cookies);
        }
        if (params != null && params.size() > 0) {
            connection.data(params);
        }
        return connection;
    }

    private HttpResult sendRequest(Connection connection) throws IOException {
        Connection.Response response = connection.execute();
        HttpResult result = new HttpResult();
        result.setCode(response.statusCode());
        result.setContent(response.body());
        return result;
    }


    public HttpResult get(String url) {
        long start = System.currentTimeMillis();
        Connection connection = getConnection(url, Connection.Method.GET, null);
        try {
            log.info("jsoup get url [" + url + "]");
            HttpResult httpResult = sendRequest(connection);
            log.info("jsoup get url [" + url + "] end cost time -[" + (System.currentTimeMillis() - start) + "]");
            return httpResult;
        } catch (IOException e) {
            log.error("jsoup get url [" + url + "] error proxy is [" + proxy + "]", e);
        }
        return null;
    }


    public HttpResult post(String url, Map<String, String> params) {
        long start = System.currentTimeMillis();
        Connection connection = getConnection(url, Connection.Method.POST, params);
        try {
            log.info("jsoup post url [" + url + "],params =[" + params + "]");
            HttpResult httpResult = sendRequest(connection);
            log.info("jsoup post url [" + url + "] end cost time -[" + (System.currentTimeMillis() - start) + "]");
            return httpResult;
        } catch (IOException e) {
            log.error("jsoup get url [" + url + "] error proxy is [" + proxy + "]", e);
            return null;
        }
    }

}
