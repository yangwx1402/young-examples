package com.young.example.spider.spider.entity.meta;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDSeedEntity implements Serializable{
    private Integer page;

    private Integer pageSize;

    private String startTime;

    private String endTime;

    private String productId = "";

    private String orderId = "";

    private String servicePin = "";

    private String keyWord = "";

    private String sessionType = "";

    private String sessionStatus = "";

    private String customer = "";

    public JDSeedEntity(){}

    public JDSeedEntity(Integer page, Integer pageSize, String startTime, String endTime) {
        this.page = page;
        this.pageSize = pageSize;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public JDSeedEntity(Integer page, Integer pageSize, String startTime, String endTime, String productId, String orderId, String servicePin, String keyWord) {
        this.page = page;
        this.pageSize = pageSize;
        this.startTime = startTime;
        this.endTime = endTime;
        this.productId = productId;
        this.orderId = orderId;
        this.servicePin = servicePin;
        this.keyWord = keyWord;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServicePin() {
        return servicePin;
    }

    public void setServicePin(String servicePin) {
        this.servicePin = servicePin;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String toString(){
        return "page="+page+",pageSize="+pageSize+",startTime="+startTime+",endTime="+endTime;
    }
}
