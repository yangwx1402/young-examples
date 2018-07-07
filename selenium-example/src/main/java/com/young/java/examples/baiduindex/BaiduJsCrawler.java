package com.young.java.examples.baiduindex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.young.java.examples.baiduindex.support.HttpResult;
import com.young.java.examples.baiduindex.support.HttpUtils;
import com.young.java.examples.baiduindex.support.HttpUtils.HttpMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shazam
 * @DATE 2018/4/18
 */
public class BaiduJsCrawler {

    private HttpUtils httpUtils = new HttpUtils();

    private static final String BAIDU_INDEX_DATA_URL
        = "http://index.baidu.com/Interface/Newwordgraph/getIndex?region=0&startdate=%s&enddate=%s&wordlist[0]=%s";

    private static final String COOKIE
        = "searchtips=1; bdshare_firstime=1504607420982; __cfduid=dc9748d62a8fe442038096baf459a96261508550928; "
        + "BAIDUID=28933BD7C1481C42D55C290E729BEEDC:FG=1; PSTM=1515773889; BIDUPSID=4C715D03A05E570761192C3B6A3CF50B;"
        + " MCITY=-131%3A; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; "
        +
        "BDUSS=RQMi1"
        +
        "-TjVEVnUxOEl2SExsS0ZuNmdObjlGaUY1UUVQdHBOZXBOdnAwd3NFV1JiQVFBQUFBJCQAAAAAAAAAAAEAAAAqI744eXlsb3Zla2lraQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACyEPFsshDxbdV; H_PS_PSSID=1435_26260_21109_20927; Hm_lvt_d101ea4d2a5c67dab98251f0b5de24dc=1530692637,1530942639; CHKFORREG=090e6a692acee8c3d919f4317c6f4a44; bdindexid=rr1mu4lr4nklepfbuj6qot9dp7; Hm_lpvt_d101ea4d2a5c67dab98251f0b5de24dc=1530947411; PSINO=2";

    private static final String BAIDU_INDEX_PASSWORD_URL = "http://index.baidu.com/Interface/api/ptbk?uniqid=%s";

    private Map<String, String> getHeader(String cookie) {
        Map<String, String> header = new HashMap<>();
        Optional.ofNullable(COOKIE).filter(StringUtils::isNotBlank).ifPresent(value -> header.put("Cookie", value));
        return header;
    }

    public String getIndexData(String startDate, String endDate, String keyword)
        throws IOException, ScriptException, NoSuchMethodException {
        String indexUrl = String.format(BAIDU_INDEX_DATA_URL, startDate, endDate, keyword);
        Map<String, String> header = getHeader(COOKIE);
        HttpResult httpResult = httpUtils.sendRequest(indexUrl, HttpMethod.GET, header, null);
        InputStream dataStream = Optional.ofNullable(httpResult).map(HttpResult::getStream).orElse(null);
        String dataString = IOUtils.toString(dataStream, "utf-8");
        JSONObject indexDataJson = Optional.ofNullable(dataString).filter(StringUtils::isNotBlank).map(
            JSON::parseObject).orElse(null);
        String uniqid = Optional.ofNullable(indexDataJson).map(json -> json.getString("uniqid")).orElse(null);
        String passwordString = Optional.ofNullable(uniqid).map(this::getPasswordString).orElse(null);
        InputStream jsFileStream = BaiduJsCrawler.class.getResourceAsStream("/baidu.js");
        String resultString = executeJs(jsFileStream, "decode", dataString, passwordString);
        return resultString;
    }

    public String getPasswordString(String uniqid) {
        try {
            String passwordUrl = String.format(BAIDU_INDEX_PASSWORD_URL, uniqid);
            Map<String, String> header = getHeader(COOKIE);
            HttpResult httpResult = httpUtils.sendRequest(passwordUrl, HttpMethod.GET, header, null);
            InputStream dataStream = Optional.ofNullable(httpResult).map(HttpResult::getStream).orElse(null);
            String dataString = IOUtils.toString(dataStream, "utf-8");
            return dataString;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T executeJs(InputStream stream, String method, Object... args)
        throws FileNotFoundException, ScriptException, NoSuchMethodException, UnsupportedEncodingException {
        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        // FileReader的参数为所要执行的js文件的路径
        engine.eval(new InputStreamReader(stream, "utf-8"));
        if (engine instanceof Invocable) {
            //从脚本引擎中返回一个给定接口的实现
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                return (T)invoke.invokeFunction(method, args);
            }
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, ScriptException, IOException {
        BaiduJsCrawler crawler = new BaiduJsCrawler();
        String result = crawler.getIndexData("20180701","20180706","黑豹");
        System.out.println(result);
    }

}
