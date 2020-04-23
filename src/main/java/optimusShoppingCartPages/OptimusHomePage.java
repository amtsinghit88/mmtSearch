package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import seleniumUtils.SeleniumUtil;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusHomePage extends BrowserInitialization
{
	@FindBy(xpath = "//span[text()= 'Search']/..")
	public WebElement searchIcon;

	@FindBy(name = "q")
	public WebElement searchTextBox;

	public OptimusHomePage()
	{
		PageFactory.initElements(driver,this);
	}


	public String homePageTitle()
	{
		return driver.getTitle();
	}

	public void clickOnSearchIcon()
	{
		if(isElementDisplayed(searchIcon)) {
			Reporter.log("User is on Optimus homepage",true);
			seleniumClick(searchIcon);}
		else { Reporter.log(searchIcon+ "Element is not visible ",true); }
	}

	public void enterTextInSearchBox(String product)
	{
		if(isElementDisplayed(searchTextBox))
		{Reporter.log("Entering the product name for search",true);
			seleniumEnterText(searchTextBox,product);
		Reporter.log("Submitted for search" + product);}
		else{ Reporter.log(searchTextBox+ "Element is not visible ",true); }
	}

}
