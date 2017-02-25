package com.young.example.spider.waiter;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class CookieFactory {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String readCookie(File cookieJsonFile) throws IOException {
        String cookieJson = FileUtils.readFileToString(cookieJsonFile,"utf-8");
        List<Map<String,Object>> cookieMap = mapper.readValue(cookieJson, new TypeReference<List<Map<String,Object>>>(){});
        StringBuilder sb = new StringBuilder();
        for(Map<String,Object> map:cookieMap){
            sb.append(map.get("name").toString()+"="+map.get("value").toString()+";");
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\script\\tmall_cookie.json";
        System.out.println(CookieFactory.readCookie(new File(filePath)));
    }
}
