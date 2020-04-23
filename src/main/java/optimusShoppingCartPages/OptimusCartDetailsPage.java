package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

import static seleniumUtils.SeleniumUtil.*;
import static seleniumUtils.SeleniumUtil.seleniumGetAttributValue;

public class OptimusCartDetailsPage extends BrowserInitialization {

	@FindBy(className = "cart__product-title")
	public WebElement addedItemName;

	@FindBy(xpath = "//dd")
	public WebElement perItemPrice;

	@FindBy(id = "updates_large_32250403881069:a06bde7dcc089f59f99d731ea9492fb9")
	public WebElement inputCartQuanatitybox;

	@FindBy(xpath = "//tr[@class = 'cart__row']")
	public WebElement cartItemRow;

	@FindBy(xpath = "//td[@class ='cart__final-price text-right small--hide']/div/span")
	public WebElement cartPrice;

    @FindBy(xpath = "//li[text()='Size: S']")
	public WebElement itemSize;


	public OptimusCartDetailsPage() { PageFactory.initElements(driver,this); }

	public String cartPageTitle() { return driver.getTitle(); }

	public String getPerItemPrice(){
		Reporter.log("Cart Item price is "+ seleniumGetText(perItemPrice),true);
		return seleniumGetText(perItemPrice);}

	public String getAddedItemname() {
		Reporter.log("Cart Item Name is "+ seleniumGetText(addedItemName),true);
		return seleniumGetText(addedItemName); }


	public String getItemQuantity() {
		String attributeName = "data-cart-item-quantity";
		try {
			Thread.sleep(6000);
			Reporter.log("Cart Item quantity is "+ seleniumGetAttributValue(cartItemRow,attributeName),true);}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return seleniumGetAttributValue(cartItemRow,attributeName);
	}

	public String getItemSize()
	{
		String size= "";
		waitForElementVisiblity(driver,itemSize,5);
		if (isElementDisplayed(itemSize))
		{
			Reporter.log("Getting cart item size",true);
			String[] sizeArray =  seleniumGetText(itemSize).split(" ");
			size = sizeArray[1];
		}
		else {
			Reporter.log(seleniumGetText(itemSize) +"Item size is not displayed",true);
		}
        return size;
	}


	public String getTotalItemPrice(){
		Reporter.log("Cart Item price is "+ seleniumGetText(cartPrice),true);
		return seleniumGetText(cartPrice);}

	public String changeCartQuantity()
	{
		String cartQuantity = "";
		waitForElementVisiblity(driver,inputCartQuanatitybox,5);
		if(isElementDisplayed(inputCartQuanatitybox)){
			seleniumClick(inputCartQuanatitybox);
			Reporter.log("Changing cart item quantity",true);
			inputCartQuanatitybox.sendKeys(Keys.UP);
			cartQuantity= getItemQuantity();
			Reporter.log("Changed item quantity is "+ cartQuantity,true);
		}
		else {
			Reporter.log(inputCartQuanatitybox +"element is not displayed",true);
		}
		return cartQuantity;
	}

}
