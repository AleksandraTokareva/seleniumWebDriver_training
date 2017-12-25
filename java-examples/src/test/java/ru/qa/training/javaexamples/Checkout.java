package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Checkout {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkout() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("img.image")).click();
            driver.findElement(By.name("add_cart_product")).click();
            Thread.sleep(1000);
            driver.get("http://localhost/litecart/en/");
        }
        driver.findElement(By.linkText("Checkout »")).click();
        List<WebElement> item = driver.findElements(By.cssSelector("td.item"));
        for (int i = 0; i < item.size(); i++) {
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(item.get(i)));
            driver.get("http://localhost/litecart/en/checkout");
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

