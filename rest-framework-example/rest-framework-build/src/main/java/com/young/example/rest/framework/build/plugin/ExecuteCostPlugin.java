package com.young.example.rest.framework.build.plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ExecuteCostPlugin extends OncePerRequestFilter {

    private static Log log = LogFactory.getLog(ExecuteCostPlugin.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        log.info(httpServletRequest.getSession()+" execute request cost time  -["+(System.currentTimeMillis()-start)+"]");
    }
}
