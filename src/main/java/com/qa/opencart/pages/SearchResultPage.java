package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By Locators

	private By productCount = By.xpath("//div[@class='product-thumb']");

	// 2.Page constructor

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3. Page actions

	public int getProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();

	}
	
	public ProductInfoPage selectProduct(String SearchProductName) {
		By product=By.linkText(SearchProductName);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
		
	}
}
