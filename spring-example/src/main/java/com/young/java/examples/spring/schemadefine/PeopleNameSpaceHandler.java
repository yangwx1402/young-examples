package com.young.java.examples.spring.schemadefine;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2016/5/22.
 */
public class PeopleNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //注册People标签xml解析器
       registerBeanDefinitionParser("people",new PeopleBeanDefinitionParser());
    }

}
