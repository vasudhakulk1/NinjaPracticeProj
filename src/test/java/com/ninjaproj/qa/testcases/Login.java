package com.ninjaproj.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ninjaproj.qa.base.Base;
import com.ninjaproj.qa.pages.AccountPage;
import com.ninjaproj.qa.pages.HomePage;
import com.ninjaproj.qa.pages.LoginPage;
import com.ninjaproj.qa.utils.Utilities;

public class Login extends Base {

	public Login () {
		super();
	}
	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndAppURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1, dataProvider="validCreditialsSupplier")
	public void verifyLoginWithValidCreditials(String email, String password) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickonLoginBtn();
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccInfo(),"Edit your informantion is not displayed");

	}
	@DataProvider(name="validCreditialsSupplier")
	public Object[][] supplyTestData() {
		Object [][] data=Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority=2)
	public void verifyLoginWithInvalidCredintials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginBtn();

		String actualWarningMessage = loginPage.retriveWarningMsgText();
		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");
		
	}
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailandValidPwd() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("validPassword"));
		loginPage.clickonLoginBtn();

		String actualWarningMessage = loginPage.retriveWarningMsgText();
		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");

	}
	@Test(priority=4)
	public void verifyLoginWithValidEmailandInvalidPwd() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginBtn();
		
		String actualWarningMessage = loginPage.retriveWarningMsgText();
		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");

	}
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickonLoginBtn();

		String actualWarningMessage = loginPage.retriveWarningMsgText();
		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected warning message is not displayed");

	}

}
