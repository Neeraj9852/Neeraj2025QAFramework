package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}

//	@DataProvider
//	public Object[][] getRegData() {
//		return new Object[][] {
//
//				{ "Neeraj", "Mishra", "sfe4@gmail.com", "98987876", "pwd1234", "yes" },
//				{ "Neeraj", "Mishra", "sfe5@gmail.com", "98987876", "pwd1234", "no" },
//				{ "Neeraj", "Mishra", "sfe6@gmail.com", "98987876", "pwd1234", "yes" }
//
//		};
//	}

	public String randomEmail() {
		Random random = new Random();
		String email = "automation" + random.nextInt(1000) + "@gmail.com";

		return email;

	}

	@DataProvider
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;

	}

	@Test(dataProvider = "getRegExcelData")
	public void UserRegtest(String firtname, String lastname, String telephone, String password, String subscribe) {
		boolean succFlag = regPage.userRegistration(firtname, lastname, randomEmail(), telephone, password, subscribe);
		Assert.assertEquals(succFlag, true);

	}
}
