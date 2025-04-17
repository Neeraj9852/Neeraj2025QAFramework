package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By Locators
	
	private By logoutLink= By.linkText("Logout");
	private By searchField=By.name("search");
	private By searchIcon=By.xpath("//button[@class='btn btn-default btn-lg']");
	
	//2.Page constructor
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	
	}
	
	//3. page actions
	
	public String getAccountPageTitle() {
		String title=eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIMEOUT);
		return title;
	}
	
	public String getAccountPageUrl() {
		String url=eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIMEOUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION);
		return url;
	}
	
	public boolean islogoutLinkExist() {
		return eleUtil.waitForElementPresence(logoutLink, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
		
		
	}
	public boolean isserachFieldExist() {
		return eleUtil.waitForElementPresence(searchField, AppConstants.SMALL_DEFAULT_TIMEOUT).isDisplayed();
		
		
	}
	
	public SearchResultPage doSearch(String productName) {
		System.out.println("Searching the product with product name: " +productName);
		eleUtil.doSendKeysWithWait(searchField, AppConstants.SMALL_DEFAULT_TIMEOUT, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
		
	}
	
	
}
