package com.young.example.spider.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by young.yang on 2017/2/26.
 */
public class SeleniumExample {
    public String login() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(
                new File("E:\\dev_tools\\webdriver\\chromedriver_win32\\chromedriver.exe")) .usingAnyFreePort().build();
        service.start();
        System.out.println(service.getUrl().toString());
        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        driver.get("https://www.taobao.com/");
        Thread.sleep(2000);
        System.out.println(driver.getPageSource());
        driver.quit();
        service.stop();
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SeleniumExample example = new SeleniumExample();
        example.login();
    }
}
