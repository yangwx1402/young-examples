package com.young.java.examples.spring.schemadefine;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by Administrator on 2016/5/22.
 */
public class PeopleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return People.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String name = element.getAttribute("name");
        String age = element.getAttribute("age");
        String id = element.getAttribute("id");
        if(StringUtils.hasText(id)){
            //把属性的值加入到Bean中去
            builder.addPropertyValue("id",id);
        }
        if(StringUtils.hasText(name)){
            builder.addPropertyValue("name",name);
        }
        if(StringUtils.hasText("age")){
            builder.addPropertyValue("age",age);
        }
    }
}
