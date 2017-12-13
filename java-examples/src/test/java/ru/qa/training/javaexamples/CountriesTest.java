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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CountriesTest {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void mySecondTest() {

        driver.get("http://localhost/litecart/admin/");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        clickToCountries();
        List<WebElement> countriesRows=driver.findElements(By.xpath("//td[5]/a"));
        List<String> countries= new ArrayList<>();
        for (int i=0;i<countriesRows.size();i++){
            countries.add(countriesRows.get(i).getText());
            //System.out.println(countriesRows.get(i).getText());
        }
        countries.sort(String::compareTo);
       // System.out.println(countries.size());

        for (int i=0;i<countriesRows.size();i++){
            String currentCountriesRows=countriesRows.get(i).getText();
            String currentCountriesList=countries.get(i);
            Assert.assertTrue(currentCountriesRows+"<>"+currentCountriesList,
                    currentCountriesRows.equals(currentCountriesList));
            //countries.add(countriesRows.get(i).getText());
            //System.out.println(countriesRows.get(i).getText());
        }
        List<WebElement> zoneCountries=driver.findElements(By.xpath("//td[6]"));
        List<Integer> countriesWithZone=new ArrayList<>();
        for(int i=0;i<zoneCountries.size();i++){
            String currentZone=zoneCountries.get(i).getText();
            if(!currentZone.equals("0")){
                countriesWithZone.add(i);
            }
        }

    }

    private void clickToCountries() {
        List<WebElement> links = driver.findElements(By.cssSelector("#app- > a"));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < links.size(); i++) {
            WebElement webElement = links.get(i);
            if (webElement.getText().equals("Countries")) {
                webElement.click();
                break;
            }
        }
    }
}

// @After
// public void stop() {
//driver.quit();
// driver = null;
// }
//}

