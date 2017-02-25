package com.young.example.spider.waiter;

import com.young.example.spider.http.Response;
import com.young.example.spider.http.support.HttpClientWalker;
import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class HttpWalkerTest {

    private HttpWalker walker = new HttpClientWalker();

    public void testwalk(String url,String cookie) throws IOException {
        Request request = new Request(url);
        Map<String,String> header = new HashMap<>();
        header.put("Cookie",cookie);
        request.setHeader(header);
        Response response = walker.get(request);
        System.out.println(response.getContent());
        System.out.println(response.getStatusCode());
    }

    public static void main(String[] args) throws IOException {
        String url = "http://kf.jd.com/baseConditionQuery/queryWaiterByGid?groupId=491754";
        String cookie = "__jdv=122270672|direct|-|none|-|1487216178669; __jda=122270672.1053303206.1478744297.1487229953.1487569657.7; __jdc=122270672; _jrda=6; 3AB9D23F7A4B3C9B=VXVBDE37PLZYYE6UX2BDDXKV4WQUCV2NSPZKVJFTQFT7BYJVVJ2EVRQ7NQ3S35W7WD7HL4PBV2EXE5Z6PRDN2WG3H4; __jdu=1053303206; TrackID=1t0lsPGQ8QheZs7jJHv62-m2WOQeAmmVjvXzSitYqFdMaHsdWDO2FthCYPROq2ATVm1FIBydkeOKF4eloMipqa3WqWzeU55ig0ZN-eJ4nvRQ; pinId=7Qvlbtti21C-oBQjYOw0VA; pin=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; unick=%E5%9C%9F%E8%B1%86%E8%A5%BF%E5%85%B0%E8%8A%B1; thor=CB5457160656BB90D8C4DD50D09443401C3C4D32B7442D286802FAFE9832A89503EE1D7ABD6DF4F2106AF29A268F373C738EA8EE7FF5F0E2AB23C68018EBFBDE17C5CB56EDF035849EB617A3F1A16A07B1EBFB3F475DE70F38D799E07865661AB90AFEF117D9F8F8D4A7B42DBE4C297E9A47BFE309C6AE6055D66A340E8F5693; _tp=UKkaqPJp2AFLg4NJI1xtj%2FIkiD1sMbaom2UYoNEFlKTk7ddRyvVjjJkat2DlqIVc; logining=1; _pst=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; ceshi3.com=53yZbxsrc-ul6BgPEReC0rHMsn6XpEE4iinKetB4gx3AowiSYTB8JP22hH_-WeUl; JSESSIONID=48AC4EA6ADAC619CD157D3A076F70A89.s1";
        HttpWalkerTest test = new HttpWalkerTest();
        test.testwalk(url,cookie);
    }
}
