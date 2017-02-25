package com.young.example.spider.waiter;

import com.young.example.spider.http.Response;
import com.young.example.spider.http.support.JsoupWalker;
import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/20.
 * 京东客服人员列表获取
 */
public class JDWaiterListExample {
    private static final String cookie = "__jdv=122270672|direct|-|none|-|1487216178669; _jrda=1; __jda=122270672.1053303206.1478744297.1487569657.1487655580.8; __jdb=122270672.9.1053303206|8.1487655580; __jdc=122270672; _jrdb=1487656200603; 3AB9D23F7A4B3C9B=VXVBDE37PLZYYE6UX2BDDXKV4WQUCV2NSPZKVJFTQFT7BYJVVJ2EVRQ7NQ3S35W7WD7HL4PBV2EXE5Z6PRDN2WG3H4; __jdu=1053303206; TrackID=1r_cZT_ybMaOQMLl3FodEQEj2P32DciT40L66S7p-IiigZ35RVdz3zMsbj9t-fc0L0K5ugbk2Kkj-au08ihXxPgdWWp2uSio1gv7cBoIRGHo; pinId=7Qvlbtti21C-oBQjYOw0VA; pin=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; unick=%E5%9C%9F%E8%B1%86%E8%A5%BF%E5%85%B0%E8%8A%B1; thor=BAAA03087B7C20DAE7D1619234D105F983ABEF57E2DE6AD51309D93DDF7DD61A597F99D4A76A53967FA63644E5EEA7C316CC9011DD7585BD60A36EB9984157F7A7EA01A675B7F9D0D4EC48F566168548EC77134EC91B97598B9B6B5800132EC4E5A9B2C9212C1CD3B51C38AA0F961E16E0BAE76E3B1B9F8EE6DC7CB094FD3A45; _tp=UKkaqPJp2AFLg4NJI1xtj%2FIkiD1sMbaom2UYoNEFlKTk7ddRyvVjjJkat2DlqIVc; logining=1; _pst=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; ceshi3.com=53yZbxsrc-ul6BgPEReC0rHMsn6XpEE4iinKetB4gx3AowiSYTB8JP22hH_-WeUl; JSESSIONID=32872F33651A3FF45AEAE43BEB4B4217.s1";
    private static HttpWalker httpWalker = new JsoupWalker();
    private static final ObjectMapper mapper = new ObjectMapper();
    String filePath = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\script\\jd_cookie.json";

    public List<CSWaiter> getWaiterList(int[] groupIds) throws IOException {
        List<CSWaiter> list = new ArrayList<CSWaiter>();
        String url = "http://kf.jd.com/baseConditionQuery/queryWaiterByGid?groupId=";
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", CookieFactory.readCookie(new File(filePath)));
        Response response = null;
        Map<String, Object> temp = null;
        List<Map<String, Object>> waiters = null;
        for (int id : groupIds) {
            response = httpWalker.get(new Request(url + id, header));
            if(response.getContent().contains("京东-欢迎登录")){
                System.out.println("Cookie已过期,需要重新登录");
                return list;
            }
            temp = mapper.readValue(response.getContent(), Map.class);
            if (temp.containsKey("waiterList")) {
                waiters = (List<Map<String, Object>>) temp.get("waiterList");
                if (!CollectionUtils.isEmpty(waiters)) {
                    for (Map<String, Object> map : waiters) {
                        list.add(new CSWaiter(map.get("pin").toString(), Integer.parseInt(map.get("id").toString())));
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        JDWaiterListExample example = new JDWaiterListExample();
        List<CSWaiter> waiters = example.getWaiterList(new int[]{491754, 466287, 459413, 412853, 397638, 325774});
        System.out.println(waiters.size());
    }
}
