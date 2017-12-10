package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MySecondTest {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void mySecondTest() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector(".name")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("ul#box-apps-menu.list-vertical span.name"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
            links = driver.findElements(By.cssSelector("span.name"));
            try {
                driver.findElement(By.cssSelector("h1"));
            } catch (NoSuchElementException ex) {
                Assert.fail("no element found");

            }
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

