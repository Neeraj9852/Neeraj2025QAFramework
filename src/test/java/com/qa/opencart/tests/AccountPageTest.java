package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{

	
	@BeforeClass
	public void accSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	

	@Test
	public void accountPageTitleTest() {
		String actTitle=accountPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
		
	}
	@Test
	public void accountPageUrlTest() {
		String actUrl=accountPage.getAccountPageUrl();
		
		Assert.assertEquals(actUrl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION),true);
	}
	
	@Test
	public void logoutLinkExistTest() {
		
	Assert.assertEquals(accountPage.islogoutLinkExist(),true);
	
	}
	
	@Test
	public void searchFieldExistTest() {
		
	Assert.assertEquals(accountPage.isserachFieldExist(),true);
	
	}
	
	@Test
	public void serachProductTest() {
		
		accountPage.doSearch("Macbook");
	}
}
