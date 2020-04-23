import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCart {

	WebDriver driver;

	@BeforeTest
	public  void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver","//Users//abhishek" +
				"//Downloads//ui-automation//chromedriver");
		 driver = new ChromeDriver();
	}

	@Test
	public void navigateToOptimusEcart()
	{
		driver.navigate().to("https://ecom-optimus.myshopify.com");
		driver.manage().window().maximize();
		System.out.println("HomePage Title " +driver.getTitle());
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//div[@class = 'password-login']/a")).click();
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("idgad");
		driver.findElement(By.id("Password")).sendKeys(Keys.ENTER);
		System.out.println("Submiited the password ");
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//span[text()= 'Search']/..")).click();
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
		WebElement element = driver.findElement(By.name("q"));
		element.click();
		element.sendKeys("shirt");
		element.sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
		List<WebElement> element1 = driver.findElements(By.xpath("//a[@class = 'full-width-link']"));
		System.out.println("Search results are displayed "+element1.size());
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(element1.get(0)));
		element1.get(0).click();
		driver.manage().timeouts().implicitlyWait(10000,TimeUnit.MILLISECONDS);
		System.out.println("Product details page "+driver.getTitle());
		System.out.println(driver.findElement(By.className("product-single__title")).getText());
		WebElement addToCartbtn = driver.findElement(By.name("add"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartbtn));
		addToCartbtn.click();
		WebElement viewCartBtn= driver.findElement
				(By.xpath("//a[@class = 'cart-popup__cta-link btn btn--secondary-accent']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn));
		viewCartBtn.click();
		System.out.println("Cart page title is "+driver.getTitle());
		System.out.println(driver.findElement(By.className("cart__product-title")).getText());


	}

//	@AfterTest
//	public void closeBrowser()
//	{
//		driver.quit();
//	}

}
