package optimusEcart;

import JavaUtils.JavaUtils;
import baseSetUp.BrowserSetUp;
import listeners.ItestListeners;
import optimusShoppingCartPages.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(ItestListeners.class)
public class ShoppingCart extends BrowserSetUp
{
	OptimusLoginPage optimusLoginPage;
	OptimusHomePage optimusHomePage;
	OptimusProductDetailsPage productDetailsPage;
	OptimusCartDetailsPage cartPage;
	SearchResultsPage optimusSrp;
	String itemName= "";

	@BeforeMethod
	@Parameters({"password"})
	public void openFeaturedItemsDetails(String password)
	{

		driver = launchApplication();
		optimusLoginPage = new OptimusLoginPage(driver);
		Reporter.log("Optimus e-cart Application is open"+ optimusLoginPage.getPageTitle(),true);
		optimusLoginPage.clickEnterUsingPasswordBtn();
		optimusLoginPage.enterPassword(password);
		optimusHomePage = new OptimusHomePage(driver);
		Reporter.log("Optimus Homepage is displayed"+ optimusHomePage.getHomePageheader(),true);
	}


	@Test(priority = 1)
	public void verifyAddFeaturedItemsToCart()
	{
		optimusHomePage = new OptimusHomePage(driver);
		itemName = optimusHomePage.getFeaturedItemName();
		optimusHomePage.clickFeaturedItemlink();
		cartPage = new OptimusCartDetailsPage(driver);
		productDetailsPage = new OptimusProductDetailsPage(driver);
		String productPageTitle = "RoundNeck Shirt 14 – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle,"Actual and expected page title is not matching");
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle,"Cart page is not matching");
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getAddedItemname(),itemName,"Expected item name is not found on cart details page");
		Reporter.log("Featured item is successfully added into the cart",true);
	}


    @Test(priority = 2)
	@Parameters({"productName"})
	public void verifyAddItemsToCart(String searchIemName)
	{
		optimusHomePage = new OptimusHomePage(driver);
		optimusHomePage.clickOnSearchIcon();
		optimusHomePage.enterTextInSearchBox(searchIemName);
		Reporter.log("Optimus search results page is displayed",true);
		optimusSrp = new SearchResultsPage(driver);
		cartPage = new OptimusCartDetailsPage(driver);
		productDetailsPage = new OptimusProductDetailsPage(driver);
		optimusSrp.openProudctDetailsPage();
		String productPageTitle = "RoundNeck Shirt – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle,"Actual and expected page title is not matching");
		String itemName = productDetailsPage.getItemName();
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle,"Actual and expected page titles are not matching");
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getAddedItemname(),itemName,"Actual and expected items name are not matching");
		Reporter.log("Item is successfully added into the cart",true);
	}

	@Test(priority = 3)
	public void verifyAddDifferentSizeItemsToCart()
	{
		optimusHomePage = new OptimusHomePage(driver);
		optimusHomePage.clickFeaturedItemlink();
		productDetailsPage = new OptimusProductDetailsPage(driver);
		String productPageTitle = "RoundNeck Shirt 14 – ecom.optimus";
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle,"Actual and expected page title is not matching");
		productDetailsPage.selectItemSize("S");
		String selectedItemSize = productDetailsPage.getSelectedItemSize();
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		cartPage = new OptimusCartDetailsPage(driver);
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle,"Actual and expected page titles are not matching");
		Reporter.log("Optimus Shopping cart is displayed",true);
		Assert.assertEquals(cartPage.getItemSize(),selectedItemSize,"Actual and expected items size is not matching");
		Reporter.log("Changed Item size is successfully added to cart",true);
	}

	@Test(priority = 4)
	@Parameters({"searchIemName"})
	public void verifyProductPrice(String searchIemName)
	{
		JavaUtils util = new JavaUtils();
		optimusHomePage= new OptimusHomePage(driver);
		optimusHomePage.clickOnSearchIcon();
		optimusHomePage.enterTextInSearchBox(searchIemName);
		Reporter.log("Optimus search results page is displayed",true);
		optimusSrp = new SearchResultsPage(driver);
		optimusSrp.openProudctDetailsPage();
		String productPageTitle = "RoundNeck Shirt – ecom.optimus";
		productDetailsPage= new OptimusProductDetailsPage(driver);
		Assert.assertEquals(productDetailsPage.productPageTitle(),productPageTitle);
		productDetailsPage.clickAddToCartBtn();
		productDetailsPage.clickViewCartBtn();
		String cartPageTitle = "Your Shopping Cart – ecom.optimus";
		cartPage = new OptimusCartDetailsPage(driver);
		Assert.assertEquals(cartPage.cartPageTitle(),cartPageTitle,"Expected page titles is not found");
		String updatedCartQuantity = cartPage.changeCartQuantity();
		float actualCartPrice = util.parseCartprice(cartPage.getTotalItemPrice());
		float expectedCartPrice = util.calculatePrice(updatedCartQuantity, cartPage.getPerItemPrice());
		Assert.assertEquals(actualCartPrice,expectedCartPrice,"Expected cart price is not matching");
		Reporter.log("Cart price is calculated Successfully",true);
	}


	@AfterMethod
	public void terminateTest(ITestResult result)
	{
		try {
			getScreenShotOfFailedTest(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeBrowser();
	}
}
