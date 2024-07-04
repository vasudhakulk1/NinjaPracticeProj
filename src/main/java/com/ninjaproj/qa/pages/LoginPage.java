package com.ninjaproj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private	WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,' alert-dismissible')]")
	private WebElement emailPasswordNotMatchWarning;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys();
	}
	public void enterPassword(String passwordTest) {
		passwordField.sendKeys();
	}
	public void clickonLoginBtn() {
		loginButton.click();
	}
	public String retriveWarningMsgText() {
		String warningText = emailPasswordNotMatchWarning.getText();
		return warningText;
	}
}
