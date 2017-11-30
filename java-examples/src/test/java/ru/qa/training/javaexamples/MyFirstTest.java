package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyFirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void myFirstTest() {

        driver.get("https://mail.ru/");
        driver.findElement(By.name("q")).sendKeys("Новости в Самаре");
        driver.findElement(By.id("search:submit")).click();


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
