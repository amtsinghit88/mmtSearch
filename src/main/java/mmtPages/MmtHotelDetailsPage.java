package mmtPages;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import seleniumUtils.SeleniumUtil;

public class MmtHotelDetailsPage extends BrowserSetUp {

    public WebElement getBookNowButton(){
        return  driver.findElement(By.id("detpg_headerright_book_now"));
    }

    public MmtHotelDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyHotelDetailsPageIsDisplayed(){
        return SeleniumUtil.isElementDisplayed(driver,getBookNowButton());
    }

    public  void switchToSrpPage(String parentWindow){
        SeleniumUtil.switchToParentWindow(driver,parentWindow);
    }
}
