package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	
	@Test
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Test
	public void loginPageUrlTest() {
		String actUrl=loginPage.getLoginPageUrl();
		
		Assert.assertEquals(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),true);
	}
	
	@Test
	public void forgotPasswordLinkTest() {
		
	Assert.assertEquals(loginPage.isForgotPwdLinkExist(),true);
	
	}
	
	@Test
	public void loginTest() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(accountPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	
	}
}
