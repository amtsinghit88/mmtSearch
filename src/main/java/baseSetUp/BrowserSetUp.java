package baseSetUp;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.relevantcodes.extentreports.LogStatus;
import fileReaderUtils.PropertiesFileReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import listeners.WebDriverListeners;
import seleniumUtils.SeleniumUtil;


public class BrowserSetUp
{
	public  static WebDriver driver;
	public static EventFiringWebDriver eventWebDriver;
	public  static WebDriverListeners eventListener;
	public static  ExtentTest test;


	public static WebDriver launchApplication()
	{
		PropertiesFileReader fis = new PropertiesFileReader();
		fis.getPropertyFile();
			driver= new  DriverBuilder(fis.getBrowserType()).getWebDriver();
		    eventWebDriver = new EventFiringWebDriver(driver);
		    eventListener = new WebDriverListeners();
		    eventWebDriver.register(eventListener);
		    eventWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(fis.getUrl());
		driver.manage().window().maximize();
		Reporter.log(driver.getTitle(),true);
		return  eventWebDriver;
	}
	public void getFailedTestScreenShot(ITestResult result) throws IOException {
		if (result.getStatus()== ITestResult.FAILURE)
			SeleniumUtil.getScreenShot(driver, result.getName());
	}


	public void closeBrowser()
	{
		Reporter.log("Closing the browser",true);
		driver.quit();
	}


}
