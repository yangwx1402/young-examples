package com.young.java.examples.http.support;

import com.young.java.examples.http.HttpWalker;
import com.young.java.examples.http.Request;
import com.young.java.examples.http.Response;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yangyong3 on 2016/12/1.
 */
public class JsoupWalker extends AbstractHttpWalker implements HttpWalker {


    private String user_agent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2";

    private int timeout = 10000;

    public JsoupWalker(String user_agent, int timeout) {
        this.user_agent = user_agent;
        this.timeout = timeout;
    }

    public JsoupWalker() {
    }

    private Connection getConnection(Request request, Connection.Method method, Map<String, String> params) {
        Connection connection = HttpConnection.connect(request.getUrl()).timeout(timeout).ignoreContentType(true);
        if (!StringUtils.isBlank(user_agent)) {
            connection.userAgent(user_agent);
        }
 //       String cookie = null;
        if(request.getHeader()!=null&&!request.getHeader().isEmpty()){
//            cookie = request.getHeader().containsKey("Cookie")?request.getHeader().get("Cookie"):null;
//            request.getHeader().remove("Cookie");
            connection.headers(request.getHeader());
        }
//        if(cookie!=null){
//            Map<String,String> cookieMap = new HashMap<String,String>();
//            String[] temp = null;
//            String[] cookies = cookie.split(";");
//            if(cookies!=null&&cookies.length>0){
//                for(String line:cookies){
//                    temp = line.split("=");
//                    if(temp!=null&&temp.length==2){
//                        cookieMap.put(temp[0],temp[1]);
//                    }
//                }
//            }
//            connection.cookies(cookieMap);
//        }
        if (params != null && params.size() > 0) {
            connection.data(params);
        }
        return connection;
    }

    private Response sendRequest(Connection connection) throws IOException {
        Connection.Response jgoupResponse = connection.execute();
        return new Response(jgoupResponse.statusCode(), jgoupResponse.body(), jgoupResponse.statusMessage());
    }


    @Override
    public Response get(Request request) throws IOException {
        checkRequest(request);
        Connection connection = getConnection(request, Connection.Method.GET, null);
        return sendRequest(connection);
    }

    @Override
    public Response post(Request request) throws IOException {
        checkRequest(request);
        Connection connection = getConnection(request, Connection.Method.POST, request.getParameters());
        return sendRequest(connection);
    }

    @Override
    public Response delete(Request request) throws IOException {
        checkRequest(request);
        Connection connection = getConnection(request, Connection.Method.DELETE, null);
        return sendRequest(connection);
    }
}
