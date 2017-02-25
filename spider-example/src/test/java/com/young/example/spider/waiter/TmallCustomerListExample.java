package com.young.example.spider.waiter;

import com.young.example.spider.http.support.JsoupWalker;
import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;
import com.young.example.spider.http.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class TmallCustomerListExample {

    private static final String cookie = "atpsida=7eebdc614f97dc2de5b8d2b7_1487667889_6;aui=2612537007;isg=AkFBvCU-_TxNOREIEe8WSXh1XI15prVg1mcrDKOWPcinimFc677FMG8IvkQz;l=AtLSizctsDuFvXMUvuP2WaK0I4O049Z9;apush09808c396ca81fe43916230bb975b391=%7B%22ts%22%3A1487667882717%2C%22parentId%22%3A1487667881711%7D;isg=An9_AkL4GxrfQh8GA9acV4IlBFPdVNMG5D2lghFMGy51IJ-iGTRjVv2zsEoh;l=AnBwrsELR298xNHyaMk0F0y/QCXCuVQD;ucn=unsz;uc1=cookie14=UoW%2FWvdARl6Sdw%3D%3D&lng=zh_CN;ubn=p;cna=qvAyEcfCWxUCAXt9GuZArSCJ;t=6682b7d16fb0a8e4dc549c6bf19a5c96;unb=2612537007;cookie2=15c1d53f1fc6258713626886e309abb6;skt=0388f03a5a511ad7;sn=%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%3A%E8%92%99%E5%A4%9A;tracknick=;uss=UIYyQi9fZIxhPqKJXw763jk9cTk68n7aO0o5mGBqNcRFI%2B8XW%2F9lF%2FjI;uc3=nk2=&id2=&lg2=;_tb_token_=de135d9e3567;tbsa=c81ad3b005a9c298ec989daf_1487667886_3;cnaui=2612537007;t=6682b7d16fb0a8e4dc549c6bf19a5c96;unb=2612537007;cookie2=15c1d53f1fc6258713626886e309abb6;v=0;_tb_token_=f4a7e1b7e95a8;t=4a910e73c3953e3633ac9adfc5850b56;csg=ad60906f;cookie2=1c4e01c9df745d05e2352bdd7ad2dc73;v=0;havana_tgc=eyJwYXRpYWxUZ2MiOnsiYWNjSW5mb3MiOnsiMCI6eyJ0Z3RJZCI6IjFhX0F4RTNra3cxSC1RaWl4cVh1ZnFRIiwiYWNjZXNzVHlwZSI6MSwibWVtYmVySWQiOjI2MTI1MzcwMDd9fX19;hl_sk=foZ_Ymfnz076M-V3rqMsew;havana_tgc=eyJwYXRpYWxUZ2MiOnsiYWNjSW5mb3MiOnsiMCI6eyJ0Z3RJZCI6IjF0UThYNWdrM29NdU54WGNEVGdIYWpnIiwiYWNjZXNzVHlwZSI6MSwibWVtYmVySWQiOjI2MTI1MzcwMDd9fX19;skt=0388f03a5a511ad7;sn=%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%3A%E8%92%99%E5%A4%9A;tracknick=;uss=UIYyQi9fZIxhPqKJXw763jk9cTk68n7aO0o5mGBqNcRFI%2B8XW%2F9lF%2FjI;uc3=sg2=U7Af3epKKemwJ1DVq%2Fe922foPAJRp5htOaXRyN5ABjg%3D&nk2=&id2=&lg2=;x=2043230365;cap=4cf9;cad=M+1X8Y94+N3XxFRqIfVf5SewqoqsIs7QVOuG/UJtaHk=0001;_lastvisited=qvAyEcfCWxUCAXt9GuZArSCJ%2C%2CauwyEfUXlG4CAXt9GuYtRSajL6kHkRi4%2Cizfaean2%2Cizfaean2%2C3%2C9d2c9354%2CauwyEfUXlG4CAXt9GuYtRSaj%2Cizfb1m3l;_umdata=C234BF9D3AFA6FE7301BE1E881F27CEF486C4F4E310B20CC6754CB0F59280C4F03C62396528D2F96CD43AD3E795C914C75627CCE84B9EA6656716FFD0F3018AC;umdata_=C234BF9D3AFA6FE7301BE1E881F27CEF486C4F4E310B20CC6754CB0F59280C4F03C62396528D2F96CD43AD3E795C914C75627CCE84B9EA6656716FFD0F3018AC;umdata_=C234BF9D3AFA6FE7301BE1E881F27CEF486C4F4E310B20CC6754CB0F59280C4F03C62396528D2F96CD43AD3E795C914C75627CCE84B9EA6656716FFD0F3018AC;aimx=qvAyEaTUxl8CAXt9GuZTBPOW_1487667882;sca=abc0b2b8;cna=qvAyEcfCWxUCAXt9GuZArSCJ;cna=qvAyEcfCWxUCAXt9GuZArSCJ;_uab_collina=148766787613060968441516;_tb_token_=de135d9e3567;";
    private static HttpWalker httpWalker = new JsoupWalker();

    public void getCustomerList() throws IOException {
        String filePath = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\script\\tmall_cookie.json";
        String url = "https://zizhanghao.taobao.com/subaccount/monitor/chatRecordJson.htm?action=/subaccount/monitor/ChatRecordQueryAction&eventSubmitDoQueryChatRealtion=anything&_tb_token_=e339bbeedf97e&_input_charset=utf-8&chatRelationQuery=%7B%22employeeNick%22%3A%22%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%22%2C%22customerNick%22%3A%22%22%2C%22start%22%3A%222017-02-21%22%2C%22end%22%3A%222017-02-21%22%2C%22beginKey%22%3Anull%2C%22endKey%22%3Anull%2C%22employeeAll%22%3Afalse%2C%22customerAll%22%3Atrue%7D&_=1487660973146";
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", CookieFactory.readCookie(new File(filePath)));
        header.put("content-language","zh-CN");
        header.put("content-type","utf-8");
        Response response = httpWalker.get(new Request(url,header,null,"utf-8"));
        System.out.println(response.getContent());
    }

    public static void main(String[] args) throws IOException {
        TmallCustomerListExample example = new TmallCustomerListExample();
        example.getCustomerList();
    }
}
