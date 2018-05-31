package com.young.example.spider.integration;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.alibaba.fastjson.JSON;

import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;
import com.young.example.spider.http.Response;
import com.young.example.spider.http.support.HttpClientWalker;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shazam
 * @DATE 2018/5/31
 */
public class BaiduIndexCrawler {

    private static final String BAIDU_INDEX_URL
        = "http://index.baidu.com/Interface/Newwordgraph/getIndex?region=0&startdate=%s&enddate=%s&wordlist[0]=%s";

    private static final String BAIDU_PASSWORD_URL = "http://index.baidu.com/Interface/api/ptbk?uniqid=%s";

    private static final String pattern = "\\pP|\\pS";

    private static final String COOKIE
        = "这里填写登录后的cookie串";

    private static final String USER_AGENT
        = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) "
        + "Chrome/66.0.3359.181 Mobile Safari/537.36";

    private HttpWalker walker = new HttpClientWalker(USER_AGENT, 10000);

    private static final int HTTP_OK = 200;

    public void crawl(String keyword, String startDate, String endDate)
        throws IOException, ScriptException, NoSuchMethodException {
        String url = String.format(BAIDU_INDEX_URL, startDate, endDate, keyword);
        String dataJsonString = doGet(url);
        String uniqid = Optional.ofNullable(dataJsonString).map(JSON::parseObject).map(
            jsonObject -> jsonObject.getString("uniqid")).orElse(null);
        if (StringUtils.isNotBlank(uniqid)) {
            String passwordUrl = String.format(BAIDU_PASSWORD_URL, uniqid);
            String passwordString = doGet(passwordUrl);
            System.out.println(passwordString);
            if(StringUtils.isNotBlank(passwordString)) {
                String decodeResult = executeJs("/script/baidu_index.js", passwordString,dataJsonString);
                System.out.println(decodeResult);
            }
        }

    }

    private String doGet(String url) throws IOException {
        Request request = new Request(url, getHeader());
        Response response = walker.get(request);
        if (response.getStatusCode() == HTTP_OK) {
            return response.getContent();
        }
        return null;
    }

    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Host", "index.baidu.com");
        header.put("Referer", "http://index.baidu.com/baidu-index-mobile/index.html");
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("Cookie", COOKIE);
        return header;
    }

    private String executeJs(String jsScriptPath, String passwordString, String indexString)
        throws ScriptException, NoSuchMethodException, IOException {
        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        // FileReader的参数为所要执行的js文件的路径
        engine.eval(new InputStreamReader(BaiduIndexCrawler.class.getResourceAsStream(jsScriptPath)));
        if (engine instanceof Invocable) {
            //从脚本引擎中返回一个给定接口的实现
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                return (String)invoke.invokeFunction("getData", passwordString, indexString);
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        BaiduIndexCrawler crawler = new BaiduIndexCrawler();
        crawler.crawl("黑豹", "20180501", "20180510");
    }
}
