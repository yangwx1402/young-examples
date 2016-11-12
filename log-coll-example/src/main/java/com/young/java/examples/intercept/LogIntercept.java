package com.young.java.examples.intercept;

import com.young.java.examples.bean.*;
import com.young.java.examples.exception.LogStorageException;
import com.young.java.examples.handler.LogHandler;
import com.young.java.examples.handler.LogStorage;
import com.young.java.examples.utils.XmlUtils;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by young.yang on 2016/11/12.
 * 日志切面
 */
public class LogIntercept {

    //默认配置文件路径
    private static final String DEFAULT_XML_FILE = LogIntercept.class.getResource("/").getPath()+"log.xml";
    //缓存所有的操作
    private static final Map<String,LogOperBean> opPools = new HashMap<String,LogOperBean>();
    //用来解析xml配置文件
    private static final XmlUtils xmlUtils = new XmlUtils(LogConfigBean.class, LogModuleBean.class,LogOperBean.class, LogHandlerBean.class, LogArgBean.class);
    //緩存所有的handler
    private static final Map<String,LogHandler> handlerPool = new Hashtable<>();
    //消息存储操作类
    private static LogStorage logStorage;
    //线程池
    private static ExecutorService exectors;

    private ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    public LogIntercept(){
        this(DEFAULT_XML_FILE);
    }

    public LogIntercept(String xmlFile){
        init(xmlFile);
    }

    /**
     * 初始化配置文件
     * @param xmlFile  xml配置文件
     */
    private void init(String xmlFile) {
        LogConfigBean logConfigBean = null;
        try {
            logConfigBean = processXml(xmlFile);
            initStorage(logConfigBean);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void initStorage(LogConfigBean logConfigBean) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(logConfigBean!=null) {
            logStorage = (LogStorage) Class.forName(logConfigBean.getStorage().getClassname()).newInstance();
            logStorage.init(logConfigBean.getStorage());
        }
    }

    private LogConfigBean processXml(String xmlFile) throws IOException {
            String xml = FileUtils.readFileToString(new File(xmlFile));
            LogConfigBean logConfigBean = xmlUtils.fromXml(xml,LogConfigBean.class);
            if(logConfigBean !=null&& !logConfigBean.getModuler().isEmpty()){
                for(LogModuleBean module : logConfigBean.getModuler()){
                    if(!module.getOpers().isEmpty()){
                        for(LogOperBean oper:module.getOpers()){
                            opPools.put(oper.getMethodsignature(),oper);
                        }
                    }
                }
            }
        return logConfigBean;
    }

    /**
     * 切入点
     */
    public void pointCut(){}

    private String getMethodSignature(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        return className+"."+method;
    }

    /**
     * 前置切面
     * @param joinPoint
     */
    public void before(JoinPoint joinPoint){
        String method = getMethodSignature(joinPoint);
        if(!opPools.containsKey(method))
            return;
        long start = System.currentTimeMillis();
        timeThreadLocal.set(start);
    }

    private void handLog(String methodSignature,JoinPoint joinPoint,LogStatus logStatus,long cost){
        Object[] args = joinPoint.getArgs();
        LogOperBean oper = opPools.get(methodSignature);
            try {
                LogMessageBean messageBean = getHandler(oper.getHandler().getClassname()).message(oper, args);
                messageBean.setStatus(logStatus);
                messageBean.setCost(cost);
                logStorage.store(messageBean);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }catch (LogStorageException e) {
                e.printStackTrace();
            }

    }

    private LogHandler getHandler(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(handlerPool.containsKey(name)){
            return handlerPool.get(name);
        }else{
            LogHandler handler = (LogHandler) Class.forName(name).newInstance();
            handlerPool.put(name,handler);
            return handler;
        }
    }

    /**
     * 后置切面
     * @param joinPoint
     */
    public void after(JoinPoint joinPoint){
        String method = getMethodSignature(joinPoint);
        if(!opPools.containsKey(method))
            return;
        long start = timeThreadLocal.get();
        long cost = System.currentTimeMillis() - start;
        handLog(method,joinPoint,LogStatus.OK,cost);
    }

    /**
     * 异常后执行
     * @param joinPoint
     */
    public void exception(JoinPoint joinPoint){
        String method = getMethodSignature(joinPoint);
        if(!opPools.containsKey(method))
            return;
        long start = timeThreadLocal.get();
        long cost = System.currentTimeMillis() - start;
        handLog(method,joinPoint,LogStatus.ERROR,cost);
    }
}
