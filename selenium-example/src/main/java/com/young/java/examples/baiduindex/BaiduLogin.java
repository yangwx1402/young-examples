package com.young.java.examples.baiduindex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author shazam
 * @DATE 2018/1/30
 */

/**
 * System.setProperty("webdriver.chrome.driver",
 * "C:\\Users\\weidiao\\Desktop\\geckodriver-v0.16.1-win64\\geckodriver.exe");
 */
public final class BaiduLogin {

    private static final String BAIDU_LOGIN_WEB_ELEMENT_ID = "TANGRAM__PSP_3__footerULoginBtn";

    private static final String BAIDU_NAME_ELEMENT_ID = "TANGRAM__PSP_3__userName";

    private static final String BAIDU_PASSWORD_ELEMENT_ID = "TANGRAM__PSP_3__password";

    private static final String BAIDU_FORM_SUBMIT_ID = "TANGRAM__PSP_3__submit";

    private static final String BAIDU_LOGIN_PAGE_URL = "https://passport.baidu.com/v2/";

    private static final int DEFAULT_WAIT_WEB_LOAD = 10;

    private static final int DEFAULT_SLEEP_TIME = 2000;

    private static final int DEFUALT_LOGIN_RETRY_TIME = 3;

    private static final String BAIDU_LOGIN_SUCCESS_PAGE = "https://passport.baidu.com/center";

    private static final String DEFAULT_COOKIE_PATH = BaiduLogin.class.getResource("/").getPath();

    private boolean loginSuccess(WebDriver webDriver) {
        webDriver.get(BAIDU_LOGIN_SUCCESS_PAGE);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_WEB_LOAD, TimeUnit.SECONDS);
        String title = webDriver.getTitle();
        System.out.println(title);
        return (title != null && title.contains("帐号设置"));
    }

    public WebDriver login(String username, String password)
        throws Exception {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_WEB_LOAD, TimeUnit.SECONDS);
        String path = DEFAULT_COOKIE_PATH + "_" + username + "cookies";
        Set<Cookie> cookies = readCookie(path);
        if (!CollectionUtils.isEmpty(cookies)) {
            cookies.forEach(cookie -> webDriver.manage().addCookie(cookie));
        }
        if (!loginSuccess(webDriver)) {
            //跳转到"账号设置"页面，说明登录成功了
            for (int loginnum = 0; loginnum < DEFUALT_LOGIN_RETRY_TIME; loginnum++) {
                doLogin(webDriver, username, password);
                Thread.sleep(5000);
                if (loginSuccess(webDriver)) {
                    //saveCookie(webDriver, path);
                    return webDriver;
                }
                if (loginnum == DEFUALT_LOGIN_RETRY_TIME - 1) {
                    throw new Exception("retry " + loginnum + " login fail so stop");
                }
            }
        }

        return webDriver;
    }

    private void doLogin(WebDriver webDriver, String username, String password) throws InterruptedException {
        webDriver.get(BAIDU_LOGIN_PAGE_URL);
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_WEB_LOAD, TimeUnit.SECONDS);
        Thread.sleep(DEFAULT_SLEEP_TIME);

        WebElement userLoginElement = webDriver.findElement(By.id(BAIDU_LOGIN_WEB_ELEMENT_ID));
        userLoginElement.click();
        Thread.sleep(DEFAULT_SLEEP_TIME);
        WebElement userNameElement = webDriver.findElement(By.id(BAIDU_NAME_ELEMENT_ID));
        userNameElement.clear();
        userNameElement.sendKeys(username);
        WebElement passwordElement = webDriver.findElement(By.id(BAIDU_PASSWORD_ELEMENT_ID));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        WebElement submitElement = webDriver.findElement(By.id(BAIDU_FORM_SUBMIT_ID));
        submitElement.submit();

    }

    private void saveCookie(WebDriver webDriver, String path) throws IOException {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
        stream.writeObject(cookies);
        stream.flush();
        stream.close();
    }

    private Set<Cookie> readCookie(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        Set<Cookie> cookies = null;
        if (file.exists()) {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
            cookies = (Set<Cookie>)stream.readObject();
        }
        return cookies;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String string = "寻梦环游记";
        System.out.println(URLEncoder.encode(string, "utf-8"));
        String temp = "01/02\n"
            + "吴京:9059";

        String[] temps = temp.split("\n");
        System.out.println(temps[0]);
        System.out.println(temps[1]);
        System.out.println(temp.replaceAll("\n",":"));
    }
}
