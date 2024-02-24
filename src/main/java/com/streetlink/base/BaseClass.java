package com.streetlink.base;


import java.io.IOException;
import java.time.Duration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.streetlink.reports.ExtentManager;
import com.streetlink.utility.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	//public static WebDriver driver;
	protected static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	ReadConfig readConfig = new ReadConfig();
	
	/**
	 * call this method to get the driver object and launch the browser
	 * @return
	 */
	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}
	
	@BeforeClass
	/**
	 * This method is for launching browsers
	 * @author rituadhikari
	 */
	public void launchBrowser() {
			if (readConfig.getBrowser().equalsIgnoreCase("Chrome")) { 
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver()); 
		} else if (readConfig.getBrowser().equalsIgnoreCase("FireFox")) {
			//WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (readConfig.getBrowser().equalsIgnoreCase("Safari")) {
			//WebDriverManager.iedriver().setup();
			driver.set(new SafariDriver());  
		}
			//Maximize the screen
			getDriver().manage().window().maximize();
			//Delete all the cookies
			getDriver().manage().deleteAllCookies();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterClass
	public void afterClass() throws IOException {
		//getDriver().quit();
	}

}
