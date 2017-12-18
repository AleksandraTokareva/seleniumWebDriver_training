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


public class AddNewProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addNewProduct() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        List<WebElement> listButton = driver.findElements(By.cssSelector("a.button"));
        for (int i = 1; i < listButton.size(); i++) {
            listButton.get(i).click();
        }
        List<WebElement> selectBox = driver.findElements(By.name("status"));
        for (int i = 0; i < selectBox.size(); i++) {
            String valueSelectBox = selectBox.get(i).getAttribute("value");
            if (valueSelectBox.equalsIgnoreCase("1")) {
                selectBox.get(i).click();
                break;
            }
        }
        driver.findElement(By.name("name[en]")).sendKeys("White Duck");
        driver.findElement(By.name("code")).sendKeys("rd006");
        List<WebElement> categories = driver.findElements(By.name("categories[]"));
        for (int j = 0; j < categories.size(); j++) {
            String valueCategories = categories.get(j).getAttribute("value");
            if (valueCategories.equalsIgnoreCase("1")) {
                categories.get(j).click();
                break;
            }
        }
        Select selectCategory = new Select(driver.findElement(By.tagName("select")));
        selectCategory.selectByValue("1");
        driver.findElement(By.name("quantity")).sendKeys("30");
        //Select selectSold = new Select(driver.findElement(By.tagName("select")));
        //selectSold.selectByValue("2");
        // driver.findElement(By.name("new_images[]")).sendKeys("C:\\white_duck.png");
        //
        //driver.findElement(By.name("save")).click();
        driver.findElement(By.linkText("Information")).click();
        Select selectManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        selectManufacturer.selectByValue("1");
        driver.findElement(By.name("short_description[en]")).sendKeys("White Duck Toy");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("White Duck Toy");
        //driver.findElement(By.name("save")).click();
        driver.findElement(By.linkText("Prices")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("1");
        driver.findElement(By.name("prices[USD]")).sendKeys("20.0000");
        //driver.findElement(By.name("save")).click();

    }

}

// @After
// public void stop() {
//driver.quit();
// driver = null;
// }
//}

