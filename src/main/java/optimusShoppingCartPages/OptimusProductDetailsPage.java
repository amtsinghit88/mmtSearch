package optimusShoppingCartPages;

import baseSetUp.BrowserInitialization;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.io.IOException;

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

	public OptimusProductDetailsPage(WebDriver driver)
	{
		this.driver= driver;
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
		{
			Reporter.log("Going to click on add to cart button",true);
			seleniumClick(addToCartBtn);
		Reporter.log(getItemName()+"Adding items to cart ",true);}
		else{
			Reporter.log(addToCartBtn+ " Element is not displayed ", true);
		}
	}

	public String getSelectedItemSize()
	{
		String size = "";
        waitForElementVisiblity(driver,productSize,5);
        if (isElementDisplayed(productSize))
        {
			seleniumClick(productSize);
		   size = getSelectedOption(productSize);
        }
        else {Reporter.log("Select item size dropdown is not displayed",true); }
       return size;
	}

	public void selectItemSize(String sizeValue)
	{
		waitForElementVisiblity(driver,productSize,5);
		if (isElementDisplayed(productSize))
		{
			seleniumClick(productSize);
			selectDropdownValue(productSize,sizeValue);
			Reporter.log("Going to add item into cart with size "+sizeValue,true);
		}
		else{Reporter.log("Select item size dropdown is not displayed",true);}
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
