package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest{

	@BeforeClass
	public void productSearchSetup() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] getProductSearchData(){
		return new Object[][] {
			
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"Samsung", "Samsung SyncMaster 941BW"}
			
		};
		
	}
	
	@DataProvider(name="ImgCountData")
	public Object[][] getImageCountData(){
		return new Object[][] {
			
			{"Macbook", "MacBook Pro",4},
			{"Macbook", "MacBook Air",4},
			{"Samsung", "Samsung SyncMaster 941BW",1}
			
		};
		
	}
	
	@Test(dataProvider="getProductSearchData",enabled=true)
	public void productSerachTest(String searchKey, String productName) {
		searchResPage=accountPage.doSearch(searchKey);
		productInfoPage=searchResPage.selectProduct(productName);
		String actProductHeaderName=productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}
	
	@Test(dataProvider="ImgCountData")
	public void productImageCountTest(String searchKey, String productName, int count) {
		searchResPage=accountPage.doSearch(searchKey);
		productInfoPage=searchResPage.selectProduct(productName);
		int actProductImageCount=productInfoPage.getProductImageCount();
		Assert.assertEquals(actProductImageCount, count);
	}
	
	@Test(enabled=false)
	public void productInfoTest() {
		searchResPage=accountPage.doSearch("Macbook");
		productInfoPage=searchResPage.selectProduct("MacBook Pro");
		
		Map<String,String> actProductInfo=productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("ProductPrice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("ProductExTaxPrice"), "Ex Tax: $2,000.00");
	
		softAssert.assertAll();
	}
}
