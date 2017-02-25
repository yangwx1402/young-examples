package com.young.example.spider.htmlunit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangyong3 on 2017/2/24.
 */
public class DateTest {

   private static final  DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");

    public static Date getDate(String string) throws ParseException {
        return dateFormat.parse(string.replaceAll("周","星期"));
    }

    public static void main(String[] args) throws ParseException {


    }
}
