package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private HashMap<String, String> productmap;

	// 1. By Locators
private By productHeader=By.xpath("//div/h1");
private By productImageCount=By.cssSelector("a.thumbnail");
private By productMetaData= By.xpath("(//div[@class='col-sm-4']/ul)[1]/li");
private By productPriceData= By.xpath("(//div[@class='col-sm-4']/ul)[2]/li");
	
	// 2.Page constructor

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3. Page actions

	public String getProductHeaderValue() {

		//By mainProduct = By.xpath("//h2[@text()='" + mainProductName + "'");
		return eleUtil.doElementGetText(productHeader);

	}
	
	public int getProductImageCount() {
		return eleUtil.getElementCount(productImageCount);
		
	}
	
	public HashMap<String, String> getProductInfo() {
		 productmap=new HashMap<String, String>();
		 //add product name in map
		 productmap.put("ProductName", getProductHeaderValue());
		 getProductMetaData();
		 getProductPriceData();
		 return productmap;
	}
	
	private void getProductMetaData() {
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		
		List<WebElement> metaDataList=eleUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			
			String text=e.getText();
			String meta[]=text.split(":");
			String key=meta[0].trim();
			String value=meta[1].trim();
			
			productmap.put(key, value);
			
		}
	}
	
	private void getProductPriceData() {
		
//		$2,000.00
//		Ex Tax: $2,000.00

		List<WebElement> metaPriceList=eleUtil.getElements(productPriceData);
		String productPrice=metaPriceList.get(0).getText().trim();
		String productExTaxPrice=metaPriceList.get(1).getText().trim();
		
		productmap.put("ProductPrice", productPrice);
		productmap.put("ProductExTaxPrice", productExTaxPrice);
	}
}
