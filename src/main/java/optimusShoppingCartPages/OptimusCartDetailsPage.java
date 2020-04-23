package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

import static seleniumUtils.SeleniumUtil.*;

public class OptimusCartDetailsPage extends BrowserInitialization {

	@FindBy(className = "cart__product-title")
	public WebElement addedItemName;

	@FindBy(xpath = "//dd")
	public WebElement itemPrice;

	@FindBy(id = "updates_large_32250403881069:a06bde7dcc089f59f99d731ea9492fb9")
	public WebElement inputCartQuanatitybox;

	@FindBy(xpath = "//tr[@class = 'cart__row']")
	public WebElement cartItemRow;

	@FindBy(xpath = "//td[@class ='cart__final-price text-right small--hide']/div/span")
	public WebElement cartPrice;

	public OptimusCartDetailsPage() { PageFactory.initElements(driver,this); }

	public String cartPageTitle() { return driver.getTitle(); }

	public String getItemPrice(){
		Reporter.log("Cart Item price is "+ seleniumGetText(cartPrice),true);
		return seleniumGetText(cartPrice);}

	public String getAddedItemname() {
		Reporter.log("Cart Item Name is "+ seleniumGetText(addedItemName),true);
		return seleniumGetText(addedItemName); }

	public String getItemQuantity() {
		Reporter.log("Cart Item quantity is "+ cartItemRow.getAttribute("data-cart-item-quantity"),true);
		return cartItemRow.getAttribute("data-cart-item-quantity"); }

	public String getTotalItemPrice(){
		Reporter.log("Cart Item price is "+ seleniumGetText(itemPrice),true);
		return seleniumGetText(itemPrice);}

	public void changeCartQuantity()
	{
		waitForElementVisiblity(driver,inputCartQuanatitybox,5);
		if(isElementDisplayed(inputCartQuanatitybox)){
			seleniumClick(inputCartQuanatitybox);
			Reporter.log("Changing cart item quantity",true);
            inputCartQuanatitybox.sendKeys(Keys.UP);
            driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);
            Reporter.log("Changed item quantity is "+ getItemQuantity(),true);
		}
		else {
			Reporter.log(inputCartQuanatitybox +"element is not displayed",true);
		}
	}

}
