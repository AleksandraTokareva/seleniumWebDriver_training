package ru.qa.training.javaexamples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class TestIE {
    private WebDriver driver1;
    private WebDriverWait wait;
    private List<WebDriver> listDrivers;

    @Before
    public void start() {
        // System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
        listDrivers = new ArrayList<>();
       listDrivers.add(new FirefoxDriver());
        //listDrivers.add(new ChromeDriver());
        //listDrivers.add(new InternetExplorerDriver());
    }

    @Test
    public void runTests() throws InterruptedException {
        for (int i = 0; i <listDrivers.size();i++){
            driver1=listDrivers.get(i);
            wait = new WebDriverWait(driver1, 10);
            myFirstTest();
            Thread.sleep(5000);
            driver1.quit();
            driver1=null;
        }
    }

    public void myFirstTest() {

        driver1.get("http://localhost/litecart/en/");
        String linkOfMainPage = driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).getAttribute("href");
        Assert.assertEquals(linkOfMainPage,"http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");

        driver1.get("http://localhost/litecart/en/");
        // driver1.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String titleOfMainPage = driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).getAttribute("title");
        driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).click();
        String titleOfImagePage = driver1.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[1]/a/img")).getAttribute("title");
        // driver2.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        //driver2.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[1]/a/img")).getAttribute("title");
        //Assert.assertEquals(driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).getAttribute("title"), driver1.findElement(By.xpath("//*[@id=\"box-product\"]/div[2]/div[1]/a/img")).getAttribute("title"));
        Assert.assertEquals(titleOfMainPage, titleOfImagePage);
        //driver1.close();

        driver1.get("http://localhost/litecart/en/");
        String priceOfMainPage = driver1.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
        driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).click();
        String priceOfImagePage = driver1.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
        Assert.assertEquals(priceOfMainPage, priceOfImagePage);

        driver1.get("http://localhost/litecart/en/");
        String priceDiscountOfMainPage = driver1.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");
        driver1.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).click();
        String priceDiscountOfImagePage = driver1.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");
        Assert.assertEquals(priceDiscountOfMainPage, priceDiscountOfImagePage);

        driver1.get("http://localhost/litecart/en/");
        String priceColorOfMainPage = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String priceLineThroughOfMainPrice = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("webkitTextDecorationsInEffect");
        Assert.assertEquals(priceColorOfMainPage, "rgb(119, 119, 119)");
        Assert.assertEquals(priceLineThroughOfMainPrice, "line-through");

        driver1.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String priceColorOfImagePage = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String priceLineThroughOfImagePrice = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("webkitTextDecorationsInEffect");
        Assert.assertEquals(priceColorOfImagePage, "rgb(102, 102, 102)");
        Assert.assertEquals(priceLineThroughOfImagePrice, "line-through");

        driver1.get("http://localhost/litecart/en/");
        String priceColorOfDiscountMainPage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String priceWeightOfDiscountMainPage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        Assert.assertEquals(priceColorOfDiscountMainPage, "rgba(204, 0, 0, 1)");
        Assert.assertEquals(priceWeightOfDiscountMainPage, "700");

        driver1.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String priceColorOfDiscountImagePage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String priceWeightOfDiscountImagePage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        Assert.assertEquals(priceColorOfDiscountImagePage, "rgba(204, 0, 0, 1)");
        Assert.assertEquals(priceWeightOfDiscountImagePage, "700");


        driver1.get("http://localhost/litecart/en/");
        String sizeOfDiscountPriceMainPage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        String sizeOfMainPriceMainPage = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        // extractNumberFromSizeString(sizeOfDiscountPrice);
        // extractNumberFromSizeString(sizeOfMainPrice);
        Assert.assertTrue(extractNumberFromSizeString(sizeOfDiscountPriceMainPage)>extractNumberFromSizeString(sizeOfMainPriceMainPage));


        driver1.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
        String sizeOfDiscountPriceImagePage = driver1.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");
        String sizeOfMainPriceImagePage = driver1.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        Assert.assertTrue(extractNumberFromSizeString(sizeOfDiscountPriceImagePage)>extractNumberFromSizeString(sizeOfMainPriceImagePage));



    }

    public Float extractNumberFromSizeString(String size) {
        if (size.contains("px")) {
            size=size.substring(0,size.length()-2);
            return Float.valueOf(size);
        }
        return 0.0F;
    }

    @After
    public void stop() {
    //    driver.quit();
        driver1 = null;
    }
}

