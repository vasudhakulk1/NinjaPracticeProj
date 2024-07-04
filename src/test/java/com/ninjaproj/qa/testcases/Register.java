package com.ninjaproj.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninjaproj.qa.base.Base;
import com.ninjaproj.qa.pages.HomePage;
import com.ninjaproj.qa.utils.Utilities;

public class Register extends Base {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndAppURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1 )
	public void verifyRegAccountWithMandatoryField() {
	
			driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
			driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
			driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
			driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNo"));
			driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
			driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
			String actualSucessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
			Assert.assertEquals(actualSucessHeading, "Your Account Has Been Created!","Account Success page is not displayed");
			
	}
	
	@Test(priority=2)
	public void verifyRegAccByProvidingAllDetails() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNo"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSucessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSucessHeading, "Your Account Has Been Created!","Account Success page is not displayed");
		
	}
	
	@Test(priority=3)
	public void verifyRegAccountWithExistingEmailAdd() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNo"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning msg regarding duplicate email not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyRegisterAccountWithoutFillingDetails() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy not displayed");
		
		String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"), "First name warning is not displayed");
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),"Last name warning is not displayed");
		
		String EmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(EmailWarning, dataProp.getProperty("EmailAddWarning"), "Email warning is not displayed");
		
		String TelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(TelephoneWarning, dataProp.getProperty("telephoneWarning"), "Telephone warning is not displayed");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),"Password warning is not displayed");
	
	}

}
