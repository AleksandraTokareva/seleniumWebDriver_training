package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


public class CheckPageOpenInNewWindow {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkPageOpenInNewWindow() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Afghanistan")).click();
        String originalWindow = driver.getWindowHandle();
        //driver.findElement(By.cssSelector("#content > form > table:nth-child(2) > tbody > tr:nth-child(6) > td > a > i")).click();
        //  wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        driver.findElement(By.xpath("//td/a")).click();
        Assert.assertEquals(driver.getWindowHandles().size(), 2);
        driver.switchTo().window(driver.getWindowHandle());
        Assert.assertEquals(driver.getWindowHandle(), originalWindow);
        driver.switchTo().window(thereIsWindowOtherThan());
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    public String thereIsWindowOtherThan() {
        Set<String> windows = driver.getWindowHandles();
        for (String  s: windows) {
            if (!s.equals(driver.getWindowHandle())) {

                return s;
            }
        }


        return "";

    }

}
//    @After
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }
//}
