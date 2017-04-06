package com.young.java.examples.meta;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/4/6.
 */
public class ServiceMeta implements Serializable {
    private String serviceUrl;

    private String serviceName;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
