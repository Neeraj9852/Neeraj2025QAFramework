package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locator

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribenewsletteryes = By.xpath("//*[@type='radio' and @name='newsletter' and @value='1']");
	private By subscribenewsletterno = By.xpath("//*[@type='radio' and @name='newsletter' and @value='0']");
	private By agreecheckbox = By.xpath("//*[@name='agree']");
	private By continuebutton = By.xpath("//*[@value='Continue']");
	private By successmessage = By.cssSelector("div#content h1");

	private By logoutlink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// Page Constructor

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//page actions
	public boolean userRegistration( String firtname, String lastname, String email, String telephone, String password, String subscribe) {
	
		eleUtil.waitForElementVisible(firstname, AppConstants.MEDIUM_DEFAULT_TIMEOUT).sendKeys(firtname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(this.subscribenewsletteryes);
		}
		
		else if(subscribe.equalsIgnoreCase("no")) {
			eleUtil.doClick(this.subscribenewsletterno);
		}
	
		eleUtil.doClick(agreecheckbox);
		eleUtil.doClick(continuebutton);
	
	String accSuccessMsg=eleUtil.waitForElementVisible(this.successmessage, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText();
	System.out.println(accSuccessMsg);
	
	if(accSuccessMsg.contains(AppConstants.REGISTER_SUCCESS_MSG)) {
		eleUtil.doClick(logoutlink);
		eleUtil.doClick(registerLink);
		return true;
	
	}
	else {
		return false;
	}
	
	}

}
