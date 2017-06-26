package com.selenium.test.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class RozetkaTests {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();
        driver.get("http://rozetka.com.ua/");
    }

    @Test
    public void SearchProduct() {

        //Search iphone and click
        WebElement element = driver.findElement(By.name("text"));
        element.sendKeys("iphone");
        System.out.println("Search value: iphone");
        driver.findElement(By.name("rz-search-button")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

         //Check filter // работает, при полной загрузке сайта
        String filter = driver.findElement(By.className("filter-parametrs-i-l-i-checkbox-active" )).getText();
        Assert.assertEquals(filter, "Apple");
        System.out.println("Filter: " + filter);

         // All search results
        String itemProd = driver.findElement(By.className("g-i-tile-l" )).getText();
        System.out.println(" **** " );
        System.out.println("Search result "  );
        System.out.println( itemProd);
        System.out.println(" **** " );


        //Go to first link
        driver.findElement(By.partialLinkText("Apple iPhone" )).click();

        // Characteristics page open
        String characteristics = driver.findElement(By.className("detail-tabs-i-title-inner" )).getText();
        Assert.assertEquals(characteristics, "Apple iPhone 5s 16GB Space Gray");
        System.out.println("Characteristics page for: " + characteristics);

        // Image is
         driver.findElement(By.className("detail-img-thumbs-l-i" )).click();


         //Rewie count
        String rewie = driver.findElement(By.className("m-tabs-i-comments" )).getText();
        System.out.println("Rewie about product: " + rewie);


         //Button buy click
        driver.findElement(By.className("btn-link-i")).click();
        String addProduct = driver.findElement(By.className("cart-title" )).getText();
        Assert.assertEquals(addProduct, "Вы добавили товар в корзину");
        System.out.println("Product: " + addProduct);

        driver.findElement(By.className("popup-close")).click();


        driver.findElement(By.id("cart-popup-block" )).click();


    }

    @AfterClass // Runs this method after all the test methods in the current class have been run
    public void tearDown() {

       driver.quit();
    }
}
