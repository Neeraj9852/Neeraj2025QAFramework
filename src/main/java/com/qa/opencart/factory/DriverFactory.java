package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	/**
	 * @param BrowserName
	 * @return this returns driver
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName =prop.getProperty("browser");
		optionsManager=new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			
		}
			else if(browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				//driver=new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
							
		}
			else {
				System.out.println("Please pass the correct browser name");
			}
		
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
		
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}
	
	/**
	 * this returns properties reference with all config properties
	 * @return this returns properties reference
	 */
		public Properties initProp() {
			
			 prop=new Properties();
			try {
			FileInputStream ip= new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return prop;
		}
		
		
		/**
		 * Take screenshot
		 */
		
		public static String getScreenshot() {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			//Users/naveenautomationlabs/Documents/workspace1/
			//String path = System.getProperty("user.dir")+"/screenshot/" + methodName + ".png";
			String path="./screenshot/"+System.currentTimeMillis()+".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}
	}
	

