package com.young.example.spider.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Set;

/**
 * Created by young.yang on 2017/2/26.
 */
public class SeleniumExample2 {
    public static void main(String[] args) throws InterruptedException {
        String user = "111";
        String pass = "222";
        String driverpath = SeleniumExample2.class.getResource("/").getPath()+ File.separator+"chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverpath);
        //System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        //System.setProperty("webdriver.ie.driver","C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
        WebDriver driver = new ChromeDriver();
        Thread.sleep(3000);
        driver.get("https://login.taobao.com/member/login.jhtml");
        Thread.sleep(3000);
        WebElement login = driver.findElement(By.cssSelector("a.forget-pwd.J_Quick2Static"));
        System.out.println(login);
        login.click();
        Thread.sleep(3000);
        WebElement username = driver.findElement(By.id("TPL_username_1"));
        System.out.println(username.toString());
        username.sendKeys(user);
        Thread.sleep(3000);
        WebElement password = driver.findElement(By.id("TPL_password_1"));
        System.out.println(password.toString());
        password.sendKeys(pass);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#J_SubmitStatic.J_Submit")).click();
        Thread.sleep(3000);
        //System.out.println(driver.getPageSource());
        Set<Cookie> cookieSet = driver.manage().getCookies();
        for(Cookie cookie:cookieSet){
            System.out.println(cookie.getName()+"="+cookie.getValue());
        }
        //driver.close();
    }
}
