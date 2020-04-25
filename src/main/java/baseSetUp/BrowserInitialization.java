package baseSetUp;

import fileReaderUtils.PropertiesFileReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import listeners.WebDriverListners;

public class BrowserInitialization
{
	public  WebDriver driver;
	public EventFiringWebDriver eventWebDriver;
	public  WebDriverListners eventListener;

	public  WebDriver launchApplication()
	{
		PropertiesFileReader fis = new PropertiesFileReader();
		fis.getPropertyFile();
		String browserName= fis.prop.getProperty("browser");
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", PropertiesFileReader.prop.getProperty("chromedriverpath"));
			this.driver= new ChromeDriver();
		    eventWebDriver = new EventFiringWebDriver(this.driver);
		     eventListener = new WebDriverListners();
	         eventWebDriver.register(eventListener);
		}
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", PropertiesFileReader.prop.getProperty("firfoxdriverpath"));
			this.driver = new FirefoxDriver();
		}
		this.eventWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(PropertiesFileReader.prop.getProperty("url"));
		driver.manage().window().maximize();
		Reporter.log(driver.getTitle(),true);
		return  eventWebDriver;
	}

	    public void getScreenShotOfFailedTest(ITestResult result) throws IOException
		{
			if(result.getStatus() == 2){
			Reporter.log("Failure detected...",true);
		    String fileName = String.format(result.getName()+"Screenshot-%s.jpg", Calendar.getInstance().getTimeInMillis());
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File(PropertiesFileReader.prop.getProperty("FailedTestScreenShort") + fileName);
		    try{
			FileHandler.copy(srcFile, destFile);
		    } catch (IOException e)
			{
			e.printStackTrace();
		    }
		    Reporter.log("Screenshot taken",true);}
			else{
				Reporter.log("Test is pass successfully",true);
			}
		}


	public  void closeBrowser()
	{
		Reporter.log("Closing the browser",true);
		driver.close();
	}


}
