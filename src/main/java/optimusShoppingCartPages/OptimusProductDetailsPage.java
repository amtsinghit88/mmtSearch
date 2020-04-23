package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import seleniumUtils.SeleniumUtil;

import java.util.concurrent.TimeUnit;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusProductDetailsPage extends BrowserInitialization
{
	@FindBy(name = "add")
	public WebElement addToCartBtn;

	@FindBy(className = "price-item price-item--regular")
	public WebElement productPriceOnDetailsPage;

	@FindBy(id = "SingleOptionSelector-0")
	public WebElement productColor;

	@FindBy(id = "SingleOptionSelector-1")
	public WebElement productSize;

	@FindBy(className = "product-single__title")
	public WebElement itemName;

	@FindBy(xpath = "//a[@class = 'cart-popup__cta-link btn btn--secondary-accent']")
    public WebElement viewCartbtn;

	public OptimusProductDetailsPage()
	{
		PageFactory.initElements(driver,this);
	}

	public String productPageTitle()
	{
		return driver.getTitle();
	}

	public String getItemName()
	{
		return seleniumGetText(itemName);
	}

	public void clickAddToCartBtn()
	{
		waitForElementVisiblity(driver,addToCartBtn,5);
		if(isElementDisplayed(addToCartBtn))
		{ seleniumClick(addToCartBtn);
		Reporter.log(getItemName()+"Adding items to cart ",true);}
		else{
			Reporter.log(addToCartBtn+ " Element is not displayed ", true);
		}
	}

	public void clickViewCartBtn()
	{
		waitForElementVisiblity(driver,viewCartbtn,5);
		if (isElementDisplayed(viewCartbtn))
		{ seleniumClick(viewCartbtn);
		Reporter.log("Navigating to the optimus cart details page",true);}
		else {
			Reporter.log(viewCartbtn+ " Element is not displayed ", true);
		}
	}
}
