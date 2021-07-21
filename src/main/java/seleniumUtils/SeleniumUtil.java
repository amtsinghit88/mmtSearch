package seleniumUtils;

import baseSetUp.BrowserSetUp;
import fileReaderUtils.PropertiesFileReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import sun.awt.www.content.image.png;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeleniumUtil extends  BrowserSetUp
{
	public static boolean isElementDisplayed(WebDriver driver, WebElement ele)
	{
		try {new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(ele));
			ele.isDisplayed();
			return true;
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			return false;
		}
	}


	public static void clickOnElement(WebDriver driver, WebElement ele)
	{
		try
		{new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed())
				ele.click();
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static void enterElementText(WebDriver driver,WebElement ele, String text)
	{
		try
		{new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed())
				ele.click();
			    ele.clear();
			    ele.sendKeys(text);
			    ele.sendKeys(Keys.ENTER);
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static void submitSearchText(WebDriver driver, WebElement ele)
		{
		try
		{new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed())
				ele.submit();
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static String getElementText(WebDriver driver, WebElement ele)
	{
		String text ="";
		try
		{new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed())
			text = ele.getText();
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
		return text;
	}

	public static String getSelectedOption(WebDriver driver, WebElement ele)
	{
		String selectedValue = "";
		try {new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isEnabled()){
			Select dropDownValue = new Select(ele);
				selectedValue= dropDownValue.getFirstSelectedOption().getText();}
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
		return selectedValue;
	}

	public static void selectDropdownValue(WebDriver driver, WebElement ele,String size)
	{
		try { new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isEnabled()){
				Select dropDownValue = new Select(ele);
				dropDownValue.selectByValue(size);}
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static String getAttributeValue(WebDriver driver, WebElement ele, String attributeName)
	{
		String attribute ="";
		try
		{new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed())
				attribute = ele.getAttribute(attributeName);
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
		return attribute;
	}

	public static void selectMonth(WebElement monthElement, String selectMonth){
			String monthName = monthElement.getText();
			if (!monthName.equals(selectMonth)){
				//click on back arrow icon
			}
			else {

			}
	}

	public static void clickGivenDay(List<WebElement> elementList, String day) {

		/**Non-functional JAVA version of this method.*/
		for (
		    WebElement cell : elementList) {
		    String cellText = cell.getText();
		    if (cellText.contains(day)) {
		        cell.click();
		        break;
		    }
		}
	}

	public static void switchToChildWindow(WebDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowId : windowHandles) {
			if (!windowId.equals(windowHandles)) {
				driver.switchTo().window(windowId);
			}
		}
	}

	public static void switchToParentWindow(WebDriver driver, String parentWindow){
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowId : windowHandles) {
			if (windowId.equals(parentWindow)) {
				driver.switchTo().window(parentWindow);
			}
		}
	}

	public  static void getScreenShot(WebDriver driver, String screenshotName){
		String datetime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir")+"/screenshots/" + "/FailedTestScreenShot/"+screenshotName + datetime+".png";
		File finalFile = new File(filePath);
		try{
			FileUtils.copyFile(srcFile, finalFile);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Reporter.log("Screenshot taken",true);
	}
}
