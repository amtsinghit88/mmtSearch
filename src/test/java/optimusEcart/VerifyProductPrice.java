package optimusEcart;

import JavaUtils.JavaUtils;
import optimusShoppingCartPages.*;
import org.testng.Assert;
import org.testng.Reporter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static browserSetUp.BrowserInitialization.closeBrowser;


public class VerifyProductPrice extends  BaseTest
{
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
	public void verifyProductprice()
	{
		JavaUtils util = new JavaUtils();
		optimusSrp = new SearchResultsPage();
		cartPage = new OptimusCartDetailsPage();
		productDetailsPage= new OptimusProductDetailsPage();
		optimusSrp.openProudctDetailsPage();
		String productPageTitle = "RoundNeck Shirt – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle);
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle);
		String updatedCartQuantity = cartPage.changeCartQuantity();
		float actualCartPrice = util.parseCartprice(cartPage.getTotalItemPrice());
		float expectedCartPrice = util.calculatePrice(updatedCartQuantity, cartPage.getPerItemPrice());
		Assert.assertEquals(actualCartPrice,expectedCartPrice);
		Reporter.log("Cart price is calculated Successfully",true);
	}

	@AfterTest
	public void terminateTest()
	{
		closeBrowser();
	}
}
