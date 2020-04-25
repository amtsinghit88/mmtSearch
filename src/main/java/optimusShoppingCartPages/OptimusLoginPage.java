package optimusShoppingCartPages;

import baseSetUp.BrowserInitialization;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.io.IOException;

import static seleniumUtils.SeleniumUtil.*;


public class OptimusLoginPage extends BrowserInitialization
{

	@FindBy(xpath = "//div[@class = 'password-login']/a")
	public WebElement enterUsingPasswordButton;

	@FindBy(id = "Password")
	public WebElement enterPasswordTextBox;

	@FindBy(name = "commit")
	public WebElement submittBtn;

	public OptimusLoginPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}

	public String getPageTitle()
	{
		return driver.getTitle();
	}

	public void clickEnterUsingPasswordBtn()
	{

		if (isElementDisplayed(enterUsingPasswordButton))
		{
			Reporter.log("Clicking on Enter using password button",true);
			seleniumClick(enterUsingPasswordButton);
		}
		else
			{
				Reporter.log(enterUsingPasswordButton+ "Element is not visible ",true);
	        }
	}


	public void enterPassword()
	{
		if(isElementDisplayed(enterPasswordTextBox))
		{
			seleniumClick(enterPasswordTextBox);
			Reporter.log("Entering password on login page",true);
        seleniumEnterText(enterPasswordTextBox,"idgad");
		}
		else
			{
			    Reporter.log(enterPasswordTextBox+ "Element is not visible ",true);
			}
	}

}
