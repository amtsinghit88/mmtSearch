package optimusEcart;

import browserSetUp.BrowserInitialization;
import optimusShoppingCartPages.OptimusHomePage;
import optimusShoppingCartPages.OptimusLoginPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class BaseTest extends BrowserInitialization
{
	OptimusLoginPage optimusLoginPage;
    OptimusHomePage optimusHomePage;


	public void launchApplicatipion()
	{
		launchBrowser();
		String loginPageTitle= "ecom.optimus – Opening Soon";
		openApplication();
		optimusLoginPage = new OptimusLoginPage();
		Assert.assertEquals(optimusLoginPage.getPageTitle(),loginPageTitle);
		Reporter.log("Optimus e-cart Application is open",true);
	}

	public void naivigateToHomePage()
	{
		optimusLoginPage = new OptimusLoginPage();
		optimusHomePage = new OptimusHomePage();
		optimusLoginPage.clickEnterUsingPasswordBtn();
		String expectedPageheader = "ECOM.OPTIMUS";
		optimusLoginPage.enterPassword();
		Assert.assertEquals(optimusHomePage.getHomePageheader(),expectedPageheader);
		Reporter.log("Optimus Homepage is displayed",true);
	}
}
