package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogsBrowserTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void logsBrowserTest() {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();

        List<WebElement> itemsElements = driver.findElements(By.xpath("//td[3]/a"));
        for (int i = 1; i < itemsElements.size(); i++) {
            itemsElements.get(i).click();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().isEmpty());
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            itemsElements = driver.findElements(By.xpath("//td[3]/a"));

        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

