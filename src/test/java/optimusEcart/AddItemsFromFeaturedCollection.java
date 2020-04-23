package optimusEcart;

import optimusShoppingCartPages.OptimusCartDetailsPage;
import optimusShoppingCartPages.OptimusHomePage;
import optimusShoppingCartPages.OptimusProductDetailsPage;
import optimusShoppingCartPages.SearchResultsPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static browserSetUp.BrowserInitialization.closeBrowser;

public class AddItemsFromFeaturedCollection extends BaseTest
{
	OptimusHomePage optimusHomePage;
	OptimusProductDetailsPage productDetailsPage;
	OptimusCartDetailsPage cartPage;
	String itemName= "";

	@BeforeTest
	public void openFeaturedItemsdetails()
	{
		launchApplicatipion();
		naivigateToHomePage();
		optimusHomePage = new OptimusHomePage();
		itemName = optimusHomePage.getFeaturedItemName();
		optimusHomePage.clickFeaturedItemlink();
	}

	@Test
	public void verifyAddFeaturedItemsToCart()
	{
		optimusHomePage = new OptimusHomePage();
		cartPage = new OptimusCartDetailsPage();
		productDetailsPage = new OptimusProductDetailsPage();
		String productPageTitle = "RoundNeck Shirt 14 – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle);
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle);
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getAddedItemname(),itemName);
		Reporter.log("Featured item is successfully added into the cart",true);
	}
	@AfterTest
	public void terminateTest()
	{
		closeBrowser();
	}
}