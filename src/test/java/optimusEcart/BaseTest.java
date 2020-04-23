package optimusEcart;

import browserSetUp.BrowserInitialization;
import optimusShoppingCartPages.OptimusLoginPage;
import org.testng.Assert;
import org.testng.Reporter;

import static browserSetUp.BrowserInitialization.launchBrowser;
import static browserSetUp.BrowserInitialization.openApplication;

public class BaseTest
{
	OptimusLoginPage optimusLoginPage;

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
		optimusLoginPage.clickEnterUsingPasswordBtn();
		optimusLoginPage.enterPassword();
		Reporter.log("Optimus Homepage is displayed",true);
	}
}
