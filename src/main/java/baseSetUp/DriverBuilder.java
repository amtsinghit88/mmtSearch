package baseSetUp;

import fileReaderUtils.PropertiesFileReader;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverBuilder
{
	private  String browser;

	public DriverBuilder(String browser) {
		this.browser = browser;
	}

	public WebDriver getWebDriver()
	{
		PropertiesFileReader fis = new PropertiesFileReader();

		WebDriver driver = null;
		switch (browser.toLowerCase())
		{
			case "chrome":
				WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				if (fis.getHeadlessMode())
				{
					chromeOptions.addArguments("--headless");
				}
				driver = new ChromeDriver(chromeOptions);
				break;

			case "firefox":
				WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--no-sandbox");
				if (fis.getHeadlessMode())
				{
					firefoxOptions.addArguments("--headless");
				}
				driver = new ChromeDriver(firefoxOptions);
				break;
		}

		return  driver;
	}
}
