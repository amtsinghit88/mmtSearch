package seleniumUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumUtil
{
	public static boolean isElementDisplayed(WebElement ele)
	{
		try {
			ele.isDisplayed();
			return true;
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			return false;
		}
	}

	public static boolean waitForElementVisiblity(WebDriver driver, WebElement ele, int time)
	{
       try
	   {
		   new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(ele));
		   return true;
	   } catch (Exception e) {
		   Reporter.log("Element is not visible waitng after " + time + " seconds.");
		   Reporter.log(""+ele);
		   return false;
	   }
	}


	public static void seleniumClick(WebElement ele)
	{
		try
		{
			if (ele.isDisplayed())
				ele.click();
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static void seleniumEnterText(WebElement ele, String text)
	{
		try
		{
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

	public static void seleniumSubmitText(WebElement ele)
		{
		try
		{
			if (ele.isDisplayed())
				ele.submit();
		}
		catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc)
		{
			exc.printStackTrace();
			Reporter.log("No such element exception :: locator value" + ele,true);
		}
	}

	public static String seleniumGetText(WebElement ele)
	{
		String text ="";
		try
		{
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

	public static String getSelectedOption(WebElement ele)
	{
		String selectedValue = "";
		try {
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

	public static void selectDropdownValue(WebElement ele,String size)
	{
		try {
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

	public static String seleniumGetAttributValue(WebElement ele, String attributeName)
	{
		return ele.getAttribute(attributeName);
	}




}
