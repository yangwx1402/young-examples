package com.young.example.spider.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/24.
 */
public class TmallHtmlUnitTest {

    private String user_agent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2";


    private static final int js_timeout = 30000;

    private static final ObjectMapper mapper = new ObjectMapper();

    public void test() throws IOException, ParseException {

        String filePath = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\script\\tmall_cookie.json";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.addRequestHeader("User-Agent", user_agent);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setJavaScriptTimeout(js_timeout);
        webClient.getOptions().setTimeout(js_timeout * 2);
        String cookieJson = FileUtils.readFileToString(new File(filePath),"utf-8");
        List<Map<String,Object>> cookieSets = mapper.readValue(cookieJson, new TypeReference<List<Map<String,Object>>>(){});
        Cookie cookie = null;
        for(Map<String,Object> map:cookieSets){
            String domain = map.get("domain")==null?null:map.get("domain").toString();
            Boolean httpOnly = map.get("httponly")==null?null: (Boolean) map.get("httponly");
            String name = map.get("name")==null?null:map.get("name").toString();
            String path = map.get("path")==null?null:map.get("path").toString();
            Boolean secure = map.get("secure")==null?null: (Boolean) map.get("secure");
            String value = map.get("value")==null?null:map.get("value").toString();
            Integer expiry = map.get("expiry")==null?0: (Integer) map.get("expiry");
            Date expires = map.get("expires")==null?null:DateTest.getDate(map.get("expires").toString());
            System.out.println(webClient.getCookieManager());
            webClient.getCookieManager().addCookie(new Cookie(domain,name,value,path,expires,secure,httpOnly));
        }
        HtmlPage page = webClient.getPage("https://zizhanghao.taobao.com/subaccount/monitor/chatRecordJson.htm?action=/subaccount/monitor/ChatRecordQueryAction&eventSubmitDoQueryChatRealtion=anything&_tb_token_=e3aee7347db54&_input_charset=utf-8&chatRelationQuery=%7B%22employeeNick%22%3A%22%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%22%2C%22customerNick%22%3A%22%22%2C%22start%22%3A%222017-02-24%22%2C%22end%22%3A%222017-02-24%22%2C%22beginKey%22%3Anull%2C%22endKey%22%3Anull%2C%22employeeAll%22%3Afalse%2C%22customerAll%22%3Atrue%7D&_=1487926556578");
        System.out.println(page.asXml());
    }
    public static void main(String[] args) throws IOException, ParseException {
        TmallHtmlUnitTest test = new TmallHtmlUnitTest();
        test.test();
    }

}
