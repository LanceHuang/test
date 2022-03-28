//package com.lance.test.selenium;
//
//
//import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
//import org.junit.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Lance
// */
//public class SeleniumTest {
//
//    private String PATH_EXE_FIREFOX = "D:\\Mozilla Firefox\\firefox.exe";
//    private String PATH_DRIVER_CHROME = "F:\\project\\selenium\\chromedriver\\chromedriver.exe";
//
//    private WebDriver driver;
//
//    @Test
//    public void test() throws InterruptedException {
//        driver.navigate().to("https://www.baidu.com/s?wd=csdn&ie=utf-8&tn=99556625_hao_pg");
//        WebElement ele = driver.findElement(By.linkText("csdn人越来越少了"));
//        ((JavascriptExecutor) driver).executeScript("alert(arguments.callee)", ele);
//    }
//
//    @Before
//    public void before() {
//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY, PATH_EXE_FIREFOX);
//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
////        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, PATH_DRIVER_CHROME);
////        WebDriver driver = new ChromeDriver();
//    }
//
//    @After
//    public void after() {
////        driver.quit();
//    }
//
//}
