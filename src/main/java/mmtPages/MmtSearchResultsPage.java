package mmtPages;

import baseSetUp.BrowserSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumUtils.SeleniumUtil;

import java.util.List;

public class MmtSearchResultsPage  extends BrowserSetUp {

    WebDriverWait wait = new WebDriverWait(driver,20);

    public WebElement getMinPriceSlider(){
        return  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//span[@class = 'input-range__slider-container'])[1]"))));
    }

    public WebElement getMinPrice(){
        return  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class = 'minValue']"))));
    }

    public  WebElement getMaxPriceSlider(){
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//span[@class = 'input-range__slider-container'])[2]"))));
    }

    public WebElement getMaxPrice(){
        return  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class = 'maxValue']"))));
    }

    public  WebElement getRemovePriceFilter(){
        return  driver.findElement(By.xpath("//ul[@class = 'appliedFilters']/li/a"));
    }

    public  WebElement getViewMoreButton(){
        return  driver.findElement(By.id("hlistpg_proptypes_show_more"));
    }

    public List<WebElement> getPopularFilterList(){
        return  driver.findElements(By.xpath("//div[@id = 'POPULAR']/ul/li/span/label"));
    }

    public WebElement getHotelSearchResults(){
        return  driver.findElement(By.xpath("//div[@class = 'listingRowOuter hotelTileDt makeRelative']"));
    }

    public  WebElement getHotelsLink(){
        return  driver.findElement(By.id("Listing_hotel_"+0));
    }


    public MmtSearchResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public  boolean verifyHotelSearchResults(){
        return SeleniumUtil.isElementDisplayed(driver,getHotelSearchResults());
    }

    public  void selectHotelsFromSearchResult(){
        System.out.println("Searching for hotels...");
        SeleniumUtil.clickOnElement(driver,getHotelsLink());
    }

    public  void selectPriceFilter() throws InterruptedException {
        Actions userAction = new Actions(driver);
        userAction.clickAndHold(getMinPriceSlider());
        userAction.moveByOffset(30,0);
        userAction.release().perform();
        Thread thread = null;
        thread.sleep(10000);
        userAction.moveToElement(getMaxPriceSlider());
        userAction.clickAndHold(getMaxPriceSlider());
        userAction.moveByOffset(-20,0);
        userAction.release().perform();
        System.out.println("Selected price range is "+ SeleniumUtil.getElementText(driver,getMinPrice())+" to "+SeleniumUtil.getElementText(driver,getMaxPrice()));
    }

    public MmtSearchResultsPage removePriceFilter(){
        SeleniumUtil.clickOnElement(driver,getRemovePriceFilter());
        return this;
    }

    public MmtSearchResultsPage viewMoreFilters(){
        SeleniumUtil.clickOnElement(driver,getViewMoreButton());
        return this;
    }

    public void selectFromPopularFilterList(){
        List<WebElement> popularFilterList = getPopularFilterList();
        popularFilterList.get(popularFilterList.size()-1).click();
    }

    public void switchToHotelDetailsPage(){
        SeleniumUtil.switchToChildWindow(driver);
    }
}
