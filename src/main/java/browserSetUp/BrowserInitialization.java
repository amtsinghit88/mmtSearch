package browserSetUp;

import fileReaderUtils.FileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class BrowserInitialization
{
	public static WebDriver driver;

	public  static void launchBrowser()
	{
		FileReader fis = new FileReader();
		fis.getPropertyFile();
		String browserName= FileReader.prop.getProperty("browser");
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver","//Users//abhishek//Downloads//ui-automation//chromedriver");
			driver= new ChromeDriver();}
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//Users//abhishek" +
					"//Downloads//ui-automation//geckodriver");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void openApplication()
	{
		driver.get(FileReader.prop.getProperty("url"));
		driver.manage().window().maximize();
		Reporter.log(driver.getTitle(),true);
	}

	public static void closeBrowser()
	{
		Reporter.log("Closing the application",true);
		driver.close();
		driver.quit();
	}


}
