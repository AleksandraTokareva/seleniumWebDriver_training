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

public class ClickMenuTest {
    private WebDriver driver;

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void mySecondTest() {

        login();
        List<WebElement> links = driver.findElements(By.cssSelector("#app- > a"));
        for (int i = 0; i < links.size(); i++) {
            links.get(i).click();
            Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
            List<WebElement> items = driver.findElements(By.cssSelector(".docs .name"));
            for (int j = 0; j < items.size(); j++) {
                items.get(j).click();
                items = driver.findElements(By.cssSelector(".docs .name"));
                Assert.assertTrue(isElementPresent(By.cssSelector("h1")));
            }
            links = driver.findElements(By.cssSelector("#app- > a"));
        }
    }

    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector(".name")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
