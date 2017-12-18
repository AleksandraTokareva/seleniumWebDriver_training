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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CreateNewUser {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newUser() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
        driver.findElement(By.name("firstname")).sendKeys("James");
        driver.findElement(By.name("lastname")).sendKeys("Reed");
        driver.findElement(By.name("address1")).sendKeys("141Cimarron trl");
        driver.findElement(By.name("postcode")).sendKeys("75063");
        driver.findElement(By.name("city")).sendKeys("Irving");
        Select select = new Select(driver.findElement(By.tagName("select")));
        select.selectByValue("US");
        //Select select1 = new Select(driver.findElement(By.xpath("//tr[5]/td[2]/select")));
        //select1.selectByValue("TX");
        driver.findElement(By.name("email")).sendKeys("tester2@test.com");
        driver.findElement(By.name("phone")).sendKeys("+15128034896");
        driver.findElement(By.name("password")).sendKeys("tester7001");
        driver.findElement(By.name("confirmed_password")).sendKeys("tester7001");
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.name("email")).sendKeys("tester2@test.com");
        driver.findElement(By.name("password")).sendKeys("tester7001");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Logout")).click();

    }
}

//@After
//public void stop() {
//driver.quit();
//driver = null;
//}
//}

