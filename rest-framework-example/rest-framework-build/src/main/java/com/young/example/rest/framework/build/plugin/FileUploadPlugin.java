package com.young.example.rest.framework.build.plugin;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/5/16.
 */
public class FileUploadPlugin extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //((HttpServletResponse)res).sendRedirect("http://www.baidu.com");
        req.getRequestDispatcher("/file/upload").forward(req,res);

    }
}
