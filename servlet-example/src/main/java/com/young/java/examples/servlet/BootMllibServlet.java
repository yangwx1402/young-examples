package com.young.java.examples.servlet;

import com.young.java.examples.classifier.NativeBayes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2016/8/1.
 */
@WebServlet(name="mllib",urlPatterns="/mllib")
/**
 1  asyncSupported	声明Servlet是否支持异步操作模式
 2	description	Servlet的描述信息
 3	displayName	Servlet的显示名称
 3	initParams	Servlet的初始化参数
 5	name	Servlet的名称
 6	urlPatterns	Servlet的访问URL
 7	value	Servlet的访问URL
 */
public class BootMllibServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NativeBayes bayes = new NativeBayes();
        bayes.runBayes();
    }
}
