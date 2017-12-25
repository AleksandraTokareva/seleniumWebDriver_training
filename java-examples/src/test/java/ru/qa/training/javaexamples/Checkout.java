package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

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
//        driver.findElement(By.cssSelector("img.image")).click();
//        //Select select = new Select(driver.findElement(By.tagName("select")));
//        //select.selectByValue("Small");
//        driver.findElement(By.name("add_cart_product")).click();
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("img.image")).click();
            //  Select select = new Select(driver.findElement(By.tagName("select")));
            //   select.selectByValue("Small");
            driver.findElement(By.name("add_cart_product")).click();
            Thread.sleep(1000);
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("http://localhost/litecart/en/");
        }
        driver.findElement(By.linkText("Checkout »")).click();
//        for (int i = 0; i < 3; i++) {
//            driver.findElement(By.name("remove_cart_item")).click();
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        }
//    }
        List<WebElement> item =driver.findElements(By.cssSelector("td.item"));
        for(int i=0;i<item.size();i++) {
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.stalenessOf(item.get(i)));
           driver.get("http://localhost/litecart/en/checkout");
        }
    }
}
//    @After
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }
//}

