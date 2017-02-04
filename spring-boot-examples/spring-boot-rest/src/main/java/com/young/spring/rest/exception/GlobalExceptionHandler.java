package com.young.spring.rest.exception;

import com.young.spring.rest.entity.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangyong3 on 2017/2/3.
 * 异常处理切面
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //异常类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    //发送异常后执行
    public RestResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return new RestResult(0,e.getMessage(),new ErrorInfo(req.getRequestURL().toString()));
    }
}
