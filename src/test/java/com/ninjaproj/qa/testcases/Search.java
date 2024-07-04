package com.ninjaproj.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninjaproj.qa.base.Base;

public class Search extends Base {
	WebDriver driver;
	
	@BeforeMethod
	public void Setup() {
	
		driver = initializeBrowserAndAppURL(prop.getProperty("browser"));	
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
		public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product HP is not displayed");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextInSearchResults"),"No Product message in search result is displayed" );
	}
	 
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,"There is no product that matches the search criteria.","No Product message in search result is displayed" );

		
	}
	
}
