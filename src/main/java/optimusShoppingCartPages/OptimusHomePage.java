package optimusShoppingCartPages;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusHomePage extends BrowserSetUp
{
	@FindBy(xpath = "//a[@class ='site-header__logo-link']")
	public WebElement homePageTitle;

	@FindBy(xpath = "//span[text()= 'Search']/..")
	public WebElement searchIcon;

	@FindBy(name = "q")
	public WebElement searchTextBox;

	@FindBy(xpath = "//ul[@class = 'grid grid--uniform grid--view-items']/li")
    public List<WebElement> featuredItemsCollectionList;

	@FindBy(xpath = "//div[text() = 'RoundNeck Shirt 14']")
	public WebElement featureItemName;

	public OptimusHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this); }

	public String getHomePageheader()
	{
		String pageTitle = "";
		waitForElementVisiblity(driver,homePageTitle,5);
		if(isElementDisplayed(homePageTitle)) {
		pageTitle = seleniumGetText(homePageTitle); }
	    else { Reporter.log("Optimus home page is not displayed",true); }
	    return pageTitle;
	}

	public void clickOnSearchIcon()
	{
		Reporter.log("Going T0 click on the search icon on home page",true);
		seleniumClick(searchIcon);
	}

	public void enterTextInSearchBox(String product)
	{
		Reporter.log("Entering the product name for search",true);
		seleniumEnterText(searchTextBox,product);
		Reporter.log("Submitted for search" + product);
	}

	public void clickFeaturedItemlink()
	{
		if(featuredItemsCollectionList.size()!=0){Reporter.log("Featured item list is displayed", true);
			Reporter.log(seleniumGetText(featureItemName)+" Clicking on product links page",true);
			seleniumClick(featuredItemsCollectionList.get(0));}
		else{ Reporter.log(featuredItemsCollectionList +"No such element is displayed",true);}
	}

	public String getFeaturedItemName()
	{
		String itemName= "";
		Reporter.log(seleniumGetText(featureItemName)+" link is displayed",true);
		   itemName = seleniumGetText(featureItemName);
		return itemName;
	}

}
