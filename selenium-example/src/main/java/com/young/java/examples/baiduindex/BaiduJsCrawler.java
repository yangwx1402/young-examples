package com.young.java.examples.baiduindex;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author shazam
 * @DATE 2018/4/18
 */
public class BaiduJsCrawler {

    private static final String userAgent
        = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) "
        + "Chrome/65.0.3325.181 Safari/537.36";

    private static final RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
        .build();

    private static HttpClientBuilder httpClientBuilder = HttpClients.custom().setUserAgent(userAgent).setMaxConnTotal(
        100)
        .setMaxConnPerRoute(100).setDefaultRequestConfig(config);

    private static final int HTTP_OK = 200;

    private static final String BAIDU_INDEX_SEARCH_URL = "http://index.baidu.com/?tpl=trend&word=%s";

    private static final String BAIDU_INDEX_PARAM_ENCODE = "gb2312";

    private static final String BAIDU_IDNEX_RESPONSE_ENCODE = "gb2312";

    private static final String BAIDU_INDEX_RAPHAEL_JS_URL = "http://index.baidu.com/static/js/raphael.js";

    private static final String BAIDU_INDEX_RES2_JS_FUNCTION_NAME = "res2Function";

    private static final String cookes
        = "searchtips=1; bdshare_firstime=1504607420982; __cfduid=dc9748d62a8fe442038096baf459a96261508550928; "
        + "BAIDUID=28933BD7C1481C42D55C290E729BEEDC:FG=1; PSTM=1515773889; BIDUPSID=4C715D03A05E570761192C3B6A3CF50B;"
        + " MCITY=-131%3A; H_PS_PSSID=1435_21109_20927; "
        + "BDSFRCVID=nz-sJeC62Z-HALrAForYKwpeRqoCbu3TH6aoAfyVTo1r3YM2yOSEEG0Pqx8g0KubtwsaogKK0mOTH65P; "
        + "H_BDCLCKID_SF=tJ48_CLMfC_3fP36q4rMM-LthfLX5-RLfKoEs4OF5l8"
        + "-hxFzyMQdKx_zhp5kBbbj5GrDWhOtbCOxOKQphUQt2qK7hNQHJhcutj59bh5N3KJmqtP9bT3v5tD35pbK2-biWbRL2MbdJD5mbRO4"
        + "-TFhD6j-Dx5; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PSINO=2; "
        + "Hm_lvt_d101ea4d2a5c67dab98251f0b5de24dc=1522674360,1524015739; FP_UID=184b6e3e8dd5e3418d06ca82debba084; "
        + "BDUSS=TQxZUZjTXlyRTV-QTZ4eTR"
        +
        "-RDhGdVJuSjZwMkZvQmF2WUR5ZGtaWGV1R1pMfjVhQVFBQUFBJCQAAAAAAAAAAAEAAAAqI744eXlsb3Zla2lraQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJmi1lqZotZab; CHKFORREG=090e6a692acee8c3d919f4317c6f4a44; Hm_lpvt_d101ea4d2a5c67dab98251f0b5de24dc=1524017038";

    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public BaiduJsCrawler() {
        webClient.getOptions().setJavaScriptEnabled(true); // 启动JS
        webClient.getOptions().setUseInsecureSSL(true);//忽略ssl认证
        webClient.getOptions().setCssEnabled(false);//禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setThrowExceptionOnScriptError(false);//运行错误时，不抛出异常
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    public static class Response {
        private int code;

        private String body;

        public Response(int code, String body) {
            this.body = body;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    private static Response doGet(String url, Map<String, String> header) throws IOException {
        HttpClient client = httpClientBuilder.build();
        HttpGet get = new HttpGet(url);
        header.entrySet().stream().forEach(entry -> get.addHeader(entry.getKey(), entry.getValue()));
        HttpResponse httpResponse = client.execute(get);
        return new Response(httpResponse.getStatusLine().getStatusCode(),
            IOUtils.toString(httpResponse.getEntity().getContent(), BAIDU_IDNEX_RESPONSE_ENCODE));
    }

    private static final Map<String, String> getHeader() {
        Map<String, String> header = new HashMap();
        header.put("Host", "index.baidu.com");
        header.put("Cache-Control", "max-age=0");
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("User-Agent", userAgent);
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Referer", "http://index.baidu.com/");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        header.put("Cookie", cookes);
        return header;
    }

    public static void searchIndex(String keyword) throws IOException, ScriptException, NoSuchMethodException {
        String searchUrl = String.format(BAIDU_INDEX_SEARCH_URL, URLEncoder.encode(keyword, BAIDU_INDEX_PARAM_ENCODE));
        Response response = doGet(searchUrl, getHeader());
        if (response.getCode() == HTTP_OK) {
            Document document = Jsoup.parse(response.getBody());
            System.out.println(findRes(document));
            System.out.println(findRes2(document));
        }
    }

    private static String findRes(Document document) {
        Elements elements = document.getElementsByTag("script");
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String content = element.data();
                if (content.contains("BID.fnsDate.adjust")) {
                    int start = content.indexOf("'");
                    int end = content.indexOf("'", start + 1);
                    return content.substring(start + 1, end);
                }
            }
        }
        return null;
    }

    private static String findRes2(Document document) throws IOException, ScriptException, NoSuchMethodException {
        String res2Js = findJs(document);
        String cleanJs = cleanRes2Js(res2Js);
        String raphaelJs = getRaphaelJs();
        String res2 = executeJs(raphaelJs, cleanJs);
        return res2;
    }

    private static String executeJs(String raphaelJs, String cleanJs) throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        System.out.println(cleanJs);
        engine.eval(cleanJs);
        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable)engine;
            return (String)invoke.invokeFunction(BAIDU_INDEX_RES2_JS_FUNCTION_NAME);
        }
        return null;
    }

    private static String cleanRes2Js(String res2Js) {
        String temp = res2Js.replaceAll("T\\(function \\(\\) \\{", "").replaceAll("BID.res2\\(\\/\\\\/\\/\\);", "")
            .replaceAll(
                "BID.scroll_resize\\(\\);", "").replaceAll(
                "BID.placehold\\('#schword', \\{cssText: 'padding:9px 40px;color:#d8d8d8;font-size:16px;"
                    + "_padding-top:8px;'}\\);",
                "").replaceAll("", "");
        temp = remove(temp, "BID.newSuggestion", "});");
        temp = remove(temp,"T(","});");
        temp = remove(temp,"T(","});");
        temp = temp.replaceAll("BID.crAdvPannel\\(\\);","");
        temp = temp.replaceAll("BID.fillAdvPannel\\(\\);","");
        temp = temp.replaceAll("BID.splitWord\\(\\);","");

        temp = remove(temp,"BID.dataBanner","});");
        temp = remove(temp,"if ( $('#compTab li.curr').index() === 1 )","});");
        temp = remove(temp,"BID.evts.care","});");
        return temp;

    }

    private static String remove(String temp, String prefix, String subfix) {
        int start = temp.indexOf(prefix);
        int end = temp.indexOf(subfix);
        String tt = temp.substring(start, end + subfix.length());
        temp = StringUtils.remove(temp, tt);
        return temp;
    }

    private static String findJs(Document document) {
        Elements elements = document.getElementsByTag("script");
        if (elements != null && elements.size() > 0) {
            for (Element element : elements) {
                String content = element.data();
                if (content.contains("BID.res2")) {
                    return content;
                }
            }
        }
        return null;
    }

    private static String getRaphaelJs() throws IOException {
        Response response = doGet(BAIDU_INDEX_RAPHAEL_JS_URL, getHeader());
        if (response.getCode() == HTTP_OK) { return response.getBody(); } else { return null; }
    }

    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        searchIndex("黑豹");
    }

}
