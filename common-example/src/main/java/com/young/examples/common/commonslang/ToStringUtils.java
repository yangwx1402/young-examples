package com.young.examples.common.commonslang;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author shazam
 * @DATE 2017/12/6
 */
public class ToStringUtils {

    public static <T> String toString(T t, ToStringStyle style){
        return ToStringBuilder.reflectionToString(t,style);
    }
    public static void main(String[] args){
     User user  = new User();
     user.setUsername("123");
     user.setAge(12);
     System.out.println(ToStringUtils.toString(user,ToStringStyle.DEFAULT_STYLE));
    }
}
