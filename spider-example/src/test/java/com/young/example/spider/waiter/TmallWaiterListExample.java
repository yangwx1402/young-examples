package com.young.example.spider.waiter;

import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;
import com.young.example.spider.http.Response;
import com.young.example.spider.http.support.JsoupWalker;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/20.
 * 天猫客服人员列表获取
 */
public class TmallWaiterListExample {

    private static final String cookie = "cna=AMeqED4tZUICAXt9GuKTIEMQ; thw=cn; ali_ab=123.125.26.230.1487573676959.6; _tb_token_=e339bbeedf97e; x=2043230365; uc3=sg2=U7Af3epKKemwJ1DVq%2Fe922foPAJRp5htOaXRyN5ABjg%3D&nk2=&id2=&lg2=; uss=U7OWaSKUAqksl92lwxLrC7dMLCpZyiD3zsSMAveTveqJWvNP%2F72%2FF5pr; tracknick=; sn=%E4%B9%90%E8%A7%86%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0%E5%BA%97%3A%E8%92%99%E5%A4%9A; skt=9f19588d5cd08665; v=0; cookie2=1b8a2eb21bccc5d7f0f48ff1858e0b8d; unb=2612537007; t=54a0371132a9690937d05963e5bbd7e1; ctoken=wvjEZj7LFISthuzUrGXhrhllor; uc1=cookie14=UoW%2FWvdAQYVxag%3D%3D&lng=zh_CN; l=AtPTBP1EsxYA5CzW15rHCf6Z4138zWdP; isg=AgQE8l3SAFx9nLQAOgvMqmSK1YIrH6RM3bkecR6k1U-XSaMTRi2rFmmv_1Zr";
    private static HttpWalker httpWalker = new JsoupWalker();

    public List<CSWaiter> getWaiterList() throws IOException {
        String filePath = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\script\\tmall_cookie.json";
        String url = "https://zizhanghao.taobao.com/subaccount/monitor/chat_record_query.htm";
        List<CSWaiter> list = new ArrayList<CSWaiter>();
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", CookieFactory.readCookie(new File(filePath)));
        Response response = httpWalker.get(new Request(url, header));
        Document document = Jsoup.parse(response.getContent());
        Element element = document.getElementById("employeeNick");
        Elements waiters = element.select("option");
        Element temp = null;
        if(waiters!=null&&waiters.size()>0){
            for(int i=0;i<waiters.size();i++){
                temp = waiters.get(i);
                list.add(getWaiter(temp));
            }
        }
        return list;
    }

    private CSWaiter getWaiter(Element temp) {
        return new CSWaiter(temp.attr("value"),1);
    }

    public static void main(String[] args) throws IOException {
        TmallWaiterListExample example = new TmallWaiterListExample();
        List<CSWaiter> waiters = example.getWaiterList();
        System.out.println(waiters.size());
    }
}
