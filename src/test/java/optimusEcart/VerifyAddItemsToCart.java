package optimusEcart;

import browserSetUp.BrowserInitialization;
import optimusShoppingCartPages.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import seleniumUtils.SeleniumUtil;

import static browserSetUp.BrowserInitialization.*;

public class VerifyAddItemsToCart  extends BaseTest{

	OptimusLoginPage optimusLoginPage;
	OptimusHomePage optimusHomePage;
	SearchResultsPage optimusSrp;
	OptimusProductDetailsPage productDetailsPage;
	OptimusCartDetailsPage cartPage;

	@BeforeTest
	public void searchProduct()
	{
		launchApplicatipion();
		naivigateToHomePage();
		optimusHomePage = new OptimusHomePage();
		optimusHomePage.clickOnSearchIcon();
		optimusHomePage.enterTextInSearchBox("shirt");
		Reporter.log("Optimus search results page is displayed",true);

	}

	@Test
	public void verifyAddItemsToCart()
	{
		optimusSrp = new SearchResultsPage();
		cartPage = new OptimusCartDetailsPage();
		productDetailsPage = new OptimusProductDetailsPage();
		optimusSrp.openProudctDetailsPage();
		String productPageTitle = "RoundNeck Shirt – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle);
		String itemName = productDetailsPage.getItemName();
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle);
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getAddedItemname(),itemName);
		Reporter.log("Item is successfully added into the cart",true);
	}
	@AfterTest
	public void terminateTest()
	{
		closeBrowser();
	}
}





//Constructor
//Static
