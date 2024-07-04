package com.ninjaproj.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id ="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id ="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id ="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id ="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id ="input-password")
	private WebElement passwordField;
	
	@FindBy(id ="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	public RegisterPage (WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String passwordConfirmText) {
		passwordField.sendKeys(passwordConfirmText);
	}
	
}
