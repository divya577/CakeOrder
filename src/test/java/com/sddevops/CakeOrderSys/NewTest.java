package com.sddevops.CakeOrderSys;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
  private WebDriver webDriver;		
 
  @Test
  public void testTitle() {
	    webDriver.navigate().to("http://localhost:8090/CakeOrderSys/HomeServlet/home");

	    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    // Find WebElement by class name: promotional-cakes
	    WebElement promotionalCakesElement = webDriver.findElement(By.className("promotional-cakes"));

	    // Verify the promotional cakes section is displayed
	    Assert.assertTrue(promotionalCakesElement.isDisplayed(), "Promotional cakes section is not displayed");

	    // Find all cake items within the promotional cakes section
	    List<WebElement> cakeItems = promotionalCakesElement.findElements(By.className("cake-item"));

	    // Assert that the number of promotional cakes is equal to 3
	    Assert.assertEquals(cakeItems.size(), 3, "The number of promotional cakes is not 3");

	    // Print out each promotional cake item for verification
	    for (WebElement cakeItem : cakeItems) {
	        System.out.println(cakeItem.getText());
	    }

	    System.out.println("Test Passed!");
	}

@BeforeTest
public void beforeTest() {
	  //Setting system properties of ChromeDriver
	  //to amend directory path base on your local file path
	  String chromeDriverDir = "C:\\Users\\divyasri\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

	  System.setProperty("webdriver.chrome.driver", chromeDriverDir);

	  //initialize FirefoxDriver at the start of test
	  webDriver = new ChromeDriver();  
}

@AfterTest
public void afterTest() {
	  //Quit the ChromeDriver and close all associated window at the end of test
	  //webDriver.close();			
}

}
