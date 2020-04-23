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

public class VerifyMultipleSizeItemsInCart extends BaseTest
{
	String itemName = "";
	OptimusHomePage optimusHomePage;
	OptimusProductDetailsPage productDetailsPage;
	OptimusCartDetailsPage cartPage;

	@BeforeTest
	public void searchProduct()
	{
		launchApplicatipion();
		naivigateToHomePage();
		optimusHomePage = new OptimusHomePage();
		itemName = optimusHomePage.getFeaturedItemName();
		optimusHomePage.clickFeaturedItemlink();
	}

	@Test
	public void verifyAddDifferentSizeItemsToCart()
	{
		optimusHomePage = new OptimusHomePage();
		cartPage = new OptimusCartDetailsPage();
		productDetailsPage = new OptimusProductDetailsPage();
		String productPageTitle = "RoundNeck Shirt 14 – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle);
		productDetailsPage.selectItemSize("S");
		String selectedItemSize = productDetailsPage.getSelectedItemSize();
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle);
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getItemSize(),selectedItemSize);
		Reporter.log("Changed Item size is successfully added to cart",true);


	}

	@AfterTest
	public void terminateTest()
	{
		closeBrowser();
	}
}
