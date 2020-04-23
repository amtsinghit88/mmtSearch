package optimusShoppingCartPages;

import browserSetUp.BrowserInitialization;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import seleniumUtils.SeleniumUtil;

import static seleniumUtils.SeleniumUtil.*;


public class OptimusLoginPage extends BrowserInitialization
{

	@FindBy(xpath = "//div[@class = 'password-login']/a")
	public WebElement enterUsingPasswordButton;

	@FindBy(id = "Password")
	public WebElement enterPasswordTextBox;

	@FindBy(name = "commit")
	public WebElement submittBtn;

	public OptimusLoginPage()
	{
		PageFactory.initElements(driver,this);
	}

	public String getPageTitle()
	{
		return driver.getTitle();
	}

	public void clickEnterUsingPasswordBtn()
	{

		if (isElementDisplayed(enterUsingPasswordButton)){
		seleniumClick(enterUsingPasswordButton);}
		else
			{
				Reporter.log(enterUsingPasswordButton+ "Element is not visible ",true);
	        }
	}


	public void enterPassword()
	{
		if(isElementDisplayed(enterPasswordTextBox)){
        seleniumClick(enterPasswordTextBox);
        Reporter.log("Submitting the password",true);
        seleniumEnterText(enterPasswordTextBox,"idgad");}
		else{
			Reporter.log(enterPasswordTextBox+ "Element is not visible ",true);
		}
       // enterPasswordTextBox.sendKeys(Keys.ENTER);
	}

}
