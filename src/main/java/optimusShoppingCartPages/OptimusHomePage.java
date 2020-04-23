package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import seleniumUtils.SeleniumUtil;

import java.util.List;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusHomePage extends BrowserInitialization
{
	@FindBy(xpath = "//span[text()= 'Search']/..")
	public WebElement searchIcon;

	@FindBy(name = "q")
	public WebElement searchTextBox;

	@FindBy(xpath = "//ul[@class = 'grid grid--uniform grid--view-items']/li")
    public List<WebElement> featuredItemsCollectionLink;

	@FindBy(xpath = "//div[text() = 'RoundNeck Shirt 14']")
	public WebElement featureItemName;

	public OptimusHomePage() { PageFactory.initElements(driver,this); }


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

	public void clickFeaturedItemlink()
	{
		if(featuredItemsCollectionLink.size()!=0){Reporter.log("Featured item list is displayed", true);
			Reporter.log(seleniumGetText(featureItemName)+" Clicking on product links page",true);
			SeleniumUtil.seleniumClick(featuredItemsCollectionLink.get(0));}
		else{ Reporter.log(featuredItemsCollectionLink+"No such element is displayed",true);}
	}

	public String getFeaturedItemName()
	{
		String itemName= "";
		if(isElementDisplayed(featureItemName))
		{ Reporter.log(seleniumGetText(featureItemName)+" link is displayed",true);
		   itemName = seleniumGetText(featureItemName);
		}
		else{Reporter.log(featureItemName+"No such element is displayed",true);}
		return itemName;
	}

}
