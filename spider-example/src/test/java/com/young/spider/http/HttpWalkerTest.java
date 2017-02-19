package com.young.spider.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class HttpWalkerTest {

    private HttpWalker walker = new HttpWalker();

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
        String url = "http://shop1-chat.jd.com/chatlog/inquiryChatlog.action?chatlogsVo.startDate=2017-02-01&endTime=2017-02-06&chatlogsVo.pin=%E4%B9%90%E8%A7%86-%E5%91%97%E9%BC%A0&chatlogsVo.customer=&chatlogsVo.sessionType=&chatlogsVo.sessionStatus=&chatlogsVo.keyWord=&chatlogsVo.commodityId=&chatlogsVo.orderId=&page=3&searchCode=0";
        String cookie = "__jda=122270672.1053303206.1478744297.1484809945.1487216179.5; __jdb=122270672.1.1053303206|5.1487216179; __jdc=122270672; __jdv=122270672|direct|-|none|-|1487216178669; _jrda=4; _jrdb=1487216179143; 3AB9D23F7A4B3C9B=VXVBDE37PLZYYE6UX2BDDXKV4WQUCV2NSPZKVJFTQFT7BYJVVJ2EVRQ7NQ3S35W7WD7HL4PBV2EXE5Z6PRDN2WG3H4; TrackID=1MnjWNtY1j1esVNVfi6VSVujaYg9sE00qyLv6e4ZMCaH00kbpkorYCT4w4J7F2HICugjKefpcJ32our4D19MkBPpVfyPcpkPGrXOLRS5uawQ; pinId=7Qvlbtti21C-oBQjYOw0VA; pin=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; unick=%E5%9C%9F%E8%B1%86%E8%A5%BF%E5%85%B0%E8%8A%B1; thor=15291A7E1DB6ACA1403F06829EA1F2BC5BA61641D90D2E12559C93287734702CE3DA854855A1ACE2EFE0984BC919FA70CB8CEA80445BA1AEA26AD3A0547C91148EC05FBE83E6EAEFE584781B69837463BC8CAABDC839A93A02CCF66FFDAF353D6CB6BC9DD1FD9B94A77EC0EBDBF9B6551D4C6B373C16135368ADFB8B5376E425; _tp=UKkaqPJp2AFLg4NJI1xtj%2FIkiD1sMbaom2UYoNEFlKTk7ddRyvVjjJkat2DlqIVc; logining=1; _pst=%E4%B9%90%E8%A7%86%E6%97%97%E8%88%B0%E5%BA%97; ceshi3.com=53yZbxsrc-ul6BgPEReC0rHMsn6XpEE4iinKetB4gx3AowiSYTB8JP22hH_-WeUl; __jdu=1053303206; Hm_lvt_711a0c7f7c1cac0f57e335cb7adb7003=1487216198; Hm_lpvt_711a0c7f7c1cac0f57e335cb7adb7003=1487216207";
        HttpWalkerTest test = new HttpWalkerTest();
        test.testwalk(url,cookie);
    }
}
