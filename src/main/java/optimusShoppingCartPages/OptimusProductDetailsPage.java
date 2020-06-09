package optimusShoppingCartPages;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusProductDetailsPage extends BrowserSetUp
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
			Reporter.log("Going to click on add to cart button",true);
			seleniumClick(addToCartBtn);
		Reporter.log(getItemName()+"Adding items to cart ",true);
	}

	public String getSelectedItemSize()
	{
		String size = "";
        waitForElementVisiblity(driver,productSize,5);
        seleniumClick(productSize);
        size = getSelectedOption(productSize);
       return size;
	}

	public void selectItemSize(String sizeValue)
	{
		waitForElementVisiblity(driver,productSize,5);
		seleniumClick(productSize);
		selectDropdownValue(productSize,sizeValue);
		Reporter.log("Going to add item into cart with size "+sizeValue,true);
	}

	public void clickViewCartBtn()
	{
		waitForElementVisiblity(driver,viewCartbtn,5);
		seleniumClick(viewCartbtn);
		Reporter.log("Navigating to the optimus cart details page",true);
	}
}
