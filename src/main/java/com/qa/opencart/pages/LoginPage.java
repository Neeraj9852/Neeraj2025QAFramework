package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locator
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//*[@value='Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	
	
	//page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//page actions
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIMEOUT);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url=eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
		
		
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		
		eleUtil.doSendKeysWithWait(emailId, AppConstants.SMALL_DEFAULT_TIMEOUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		//return eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIMEOUT);
		return new AccountsPage(driver);
		
	}
	
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
	}
	
	
}
