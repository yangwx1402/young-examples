package com.young.spring.cloud.consumer.feignsupport;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by young.yang on 2017/2/19.
 * Feign采用声明式接口编程，这里就声明了provider的接口
 */
@FeignClient(name = "compute-service")
public interface ComputerClient {
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    /**
     * 這些聲明要跟服務端接口的信息匹配，其實feign在後端幫我們調用了rest接口
     */
    Integer add(@RequestParam(value = "first") Integer a, @RequestParam(value = "second") Integer b);
}
