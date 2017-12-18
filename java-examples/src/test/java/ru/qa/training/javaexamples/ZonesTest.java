package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;


public class ZonesTest {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void myZoneTest() {

        login();
        List<WebElement> zones = driver.findElements(By.xpath("//td[3]/a"));
        for (int i = 0; i < zones.size(); i++) {
            zones.get(i).click();
            List<WebElement> zoneRows = driver.findElements(By.xpath("//td[3]/select"));
            List<String> zonesList = new ArrayList<>();
            for (int j = 0; j < zoneRows.size(); j++) {
                zonesList.add(zoneRows.get(j).getText());
            }
            zonesList.sort(String::compareTo);
            for (int j = 0; j < zones.size(); j++) {
                String currentZoneRows = zoneRows.get(j).getText();
                String currentZoneList = zonesList.get(j);
                Assert.assertTrue(currentZoneRows + "<>" + currentZoneList,
                        currentZoneRows.equals(currentZoneList));
            }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            zones = driver.findElements(By.xpath("//td[3]/a"));
        }
    }

    public void login() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

