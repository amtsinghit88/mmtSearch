package listeners;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.WebDriverEventListener;
import fileReaderUtils.PropertiesFileReader;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class WebDriverListeners extends BrowserSetUp implements WebDriverEventListener   {

	PropertiesFileReader fileReader = new PropertiesFileReader();
	@Override
	public void beforeAlertAccept(WebDriver driver) {

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {

	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		try
		{
			/* Take screenshot when exception happened. */
			String fileName = String.format("Screenshot-%s.jpg", Calendar.getInstance().getTimeInMillis());
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			/* save screenshot to file. */
			FileHandler.copy(scrFile, new File(PropertiesFileReader.prop.getProperty("ActionScreenShots") +fileName));
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {

	}

	@Override
	public void afterScript(String script, WebDriver driver) {

	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) { }

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {

	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {

	}
}
