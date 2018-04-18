package com.young.java.examples.baiduindex;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author shazam
 * @DATE 2018/1/31
 */
public class BaiduIndexCrawler {

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private static final String BAIDU_INDEX_SEARCH_URL = "http://index.baidu.com/baidu-index-mobile/?#/compare/";

    private static final int DEFAULT_WAIT_WEB_LOAD = 10;

    private static final int DEFAULT_SLEEP_TIME = 2000;

    private static final String URL_ENCODE = "utf-8";

    private static final String BAIDU_GRAPH_CANVAS_ID = "canvas";

    private static final Random rand = new Random();

    private static final int INIT_WIDTH = 10;

    private static final int INIT_HEIGHT = 100;

    private static final int MAX_WIDTH = 1400;

    private static final int RAND_HEIGHT_STEP = 50;

    private static final int RAND_WIDTH_STEP = 3;

    private static final int STEP_SLEEP_TIME = 50;

    private static final int STEP_SLEEP_INTERVAL = 5;

    public Map<String, Set<String>> crawl(WebDriver webDriver, List<String> keywords)
        throws Exception {
        Map<String, Set<String>> mapping = new HashMap<>();

        if (webDriver == null) {
            throw new Exception("login fail");
        }
        for (String keyword : keywords) {
            String url = BAIDU_INDEX_SEARCH_URL + URLEncoder.encode(keyword, URL_ENCODE) + "/90";
            webDriver.get("https://www.baidu.com");
            webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_WEB_LOAD, DEFAULT_TIME_UNIT);
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_WEB_LOAD, DEFAULT_TIME_UNIT);
            try {
                WebElement firstViewElement = webDriver.findElement(By.cssSelector("div.bg-img-con"));
                if (firstViewElement != null) {
                    firstViewElement.click();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(DEFAULT_SLEEP_TIME);
            WebElement graphElement = webDriver.findElement(By.tagName(BAIDU_GRAPH_CANVAS_ID));
            Actions action = new Actions(webDriver);
            int width = INIT_WIDTH;
            action.moveToElement(graphElement, INIT_WIDTH, INIT_HEIGHT).perform();
            WebElement valueElements = webDriver.findElement(By.cssSelector("div#trendChart"));
            Set<String> set = new HashSet<>();
            while (width < MAX_WIDTH) {
                width += RAND_WIDTH_STEP;
                int height = INIT_HEIGHT + rand.nextInt(RAND_HEIGHT_STEP);
                action.moveToElement(graphElement, width, height).perform();
                System.out.println(width + "," + height);
                String value = valueElements.getText();
                if (!StringUtils.isBlank(value)) {
                    value = value.replaceAll("\n", ":");
                    set.add(value);
                    System.out.println(value);
                }
                Thread.sleep(STEP_SLEEP_TIME * rand.nextInt(STEP_SLEEP_INTERVAL));
            }
            mapping.put(keyword, set);
        }
        return mapping;
    }

    public static void main(String[] args)
        throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/user/yangyong/dev/chromedriver/chromedriver");
        BaiduIndexCrawler indexCrawler = new BaiduIndexCrawler();
        String username = "xxx";
        String password = "xxx";
        BaiduLogin login = new BaiduLogin();
        WebDriver webDriver = login.login(username, password);
        List<String> keywords = Arrays.asList("前任3",
            "移动迷宫3",
            "神秘巨星",
            "无问西东",
            "公牛历险记",
            "奇迹男孩",
            "勇敢者游戏",
            "S4侠降魔记",
            "芳华",
            "谜巢",
            "大雪冬至",
            "第一夫人",
            "妖猫传",
            "妖铃铃",
            "心理罪之城市之光",
            "帕丁顿熊2",
            "机器之血",
            "奇门遁甲",
            "星球大战",
            "解忧杂货店",
            "巨额来电",
            "太空救援",
            "迷镇凶案",
            "星球大战",
            "英雄本色2018",
            "卧底巨星",
            "寻梦环游记",
            "正义联盟",
            "追捕",
            "引爆者",
            "不成问题的问题",
            "暴雪将至",
            "降魔传",
            "烟花");
        Map<String, Set<String>> mapping = indexCrawler.crawl(webDriver, keywords);
        System.out.println(mapping);
        webDriver.quit();
        webDriver.close();

    }
}
