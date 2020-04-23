package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import seleniumUtils.SeleniumUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static seleniumUtils.SeleniumUtil.*;

public class SearchResultsPage  extends BrowserInitialization
{

	@FindBy(xpath = "//a[@class = 'full-width-link']")
	public List<WebElement> searchResultsList;


	@FindBy(xpath = "(//span[@class = 'price-item price-item--regular'])[1]")
	public WebElement productPrice;


	public SearchResultsPage()
	{
		PageFactory.initElements(driver,this);
	}

	public String srpPageTitle()
	{
		return driver.getTitle();
	}

	public void getProductPrice()
	{
		String price = seleniumGetText(productPrice);
	}


	public void openProudctDetailsPage()
	{
		if(searchResultsList.size()!=0)
		{ Reporter.log("Search results are displayed", true);
			Reporter.log("Clicking on "+seleniumGetText(searchResultsList.get(0))+" product links page",true);
			SeleniumUtil.seleniumClick(searchResultsList.get(0));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);}
		else { Reporter.log("Search results are not displayed",true); }

	}

}
