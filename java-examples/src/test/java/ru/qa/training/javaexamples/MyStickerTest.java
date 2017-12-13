package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyStickerTest {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void myStickerTest() {
        driver.get("http://localhost/litecart");
        List<WebElement> items = driver.findElements(By.cssSelector(".image-wrapper"));

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

