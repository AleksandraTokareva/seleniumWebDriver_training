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
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyStickerTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement elementwait;

    boolean isElementPresent(WebDriver driver, By locator) {
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
        //wait = new WebDriverWait(driver, 10);
        //elementwait = wait.until(presenceOfElementLocated(By.cssSelector("name")));
    }


    @Test
    public void myStickerTest() {
        driver.get("http://localhost/litecart");
        //List<WebElement> stickers = driver.findElements(By.cssSelector("img.image")).findElement(By.cssSelector("sticker"));
        List<WebElement> items = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < items.size(); i++) {

            List<WebElement> stickers = items.get(i).findElements(By.cssSelector("div.sticker"));
            Assert.assertTrue(stickers.size() == 1);


        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;


    }
}

