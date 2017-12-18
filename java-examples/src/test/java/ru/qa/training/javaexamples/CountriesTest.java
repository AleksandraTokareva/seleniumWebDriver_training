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


public class CountriesTest {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void mySecondTest() {

        login();
        List<WebElement> countriesRows = driver.findElements(By.xpath("//td[5]/a"));
        List<String> countries = new ArrayList<>();
        for (int i = 0; i < countriesRows.size(); i++) {
            countries.add(countriesRows.get(i).getText());
        }
        countries.sort(String::compareTo);

        for (int i = 0; i < countriesRows.size(); i++) {
            String currentCountriesRows = countriesRows.get(i).getText();
            String currentCountriesList = countries.get(i);
            Assert.assertTrue(currentCountriesRows + "<>" + currentCountriesList,
                    currentCountriesRows.equals(currentCountriesList));
        }
        List<WebElement> zoneCountries = driver.findElements(By.xpath("//td[6]"));
        List<Integer> countriesWithZone = new ArrayList<>();
        for (int i = 0; i < zoneCountries.size(); i++) {
            String currentZone = zoneCountries.get(i).getText();
            if (!currentZone.equals("0")) {
                countriesWithZone.add(i);
            }
        }
        for (Integer index : countriesWithZone) {

            countriesRows.get(index).click();
            testStatesOrder();

            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            countriesRows = driver.findElements(By.xpath("//td[5]/a"));
        }
    }

    public void login() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
    }

    public void testStatesOrder() {
        List<String> states = new ArrayList<>();
        List<WebElement> statesRows = driver.findElements(By.xpath("//td[3]"));
        for (int i = 1; i < statesRows.size() - 1; i++) {
            states.add(statesRows.get(i).getText());
        }
        states.sort(String::compareTo);

        for (int i = 1; i < statesRows.size() - 1; i++) {
            String currentStateRows = statesRows.get(i).getText();
            String currentStateList = states.get(i - 1);
            Assert.assertTrue(currentStateRows + "<>" + currentStateList,
                    currentStateRows.equals(currentStateList));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

