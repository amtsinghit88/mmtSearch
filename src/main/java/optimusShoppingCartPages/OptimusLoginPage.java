package optimusShoppingCartPages;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import static seleniumUtils.SeleniumUtil.*;


public class OptimusLoginPage extends BrowserSetUp
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
		Reporter.log("Clicking on Enter using password button",true);
		seleniumClick(enterUsingPasswordButton);
	}


	public void enterPassword(String password)
	{
		seleniumClick(enterPasswordTextBox);
		Reporter.log("Entering password on login page",true);
        seleniumEnterText(enterPasswordTextBox, password);

	}

}
