package com.young.java.examples.servicemanagement;

import com.young.java.examples.meta.ServiceMeta;

/**
 * Created by yangyong3 on 2017/4/6.
 */
public interface ServiceRegistry {
    void register(ServiceMeta meta) throws ServiceRegisterException;
}
