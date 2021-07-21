package mmtSearch;

import JavaUtils.JavaUtils;
import baseSetUp.BrowserSetUp;
import listeners.ItestListeners;
import mmtPages.MmtHotelDetailsPage;
import mmtPages.MmtSearchPage;
import mmtPages.MmtSearchResultsPage;
import mmtPages.SearchResultsPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import seleniumUtils.SeleniumUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(ItestListeners.class)
public class HotelSearch extends BrowserSetUp{

    @BeforeClass
    public void feedSearch(){
        MmtSearchPage searchPage = new MmtSearchPage(driver);
        driver = launchApplication();
        searchPage.selectHotelTab().clickOnMmtLogo().selectCheckInCheckOutDate(4);
        searchPage.selectGuests("2","1","3").searchForHotels();
    }

    @Test(priority = 1)
    public void verifySearch() {
        MmtSearchResultsPage mmtSrpPage = new MmtSearchResultsPage(driver);
        Assert.assertTrue("Search results displayed ", mmtSrpPage.verifyHotelSearchResults());
    }

    @Test(enabled = false)
    public void verifyPriceSearch() throws InterruptedException {
        MmtSearchResultsPage mmtSrpPage = new MmtSearchResultsPage(driver);
        MmtHotelDetailsPage mmtHotelDetailsPage = new MmtHotelDetailsPage(driver);
        mmtSrpPage.selectPriceFilter();
        Assert.assertTrue("Search results displayed after price filter applied", mmtSrpPage.verifyHotelSearchResults());
        String parentWindow = driver.getWindowHandle();
        mmtSrpPage.selectHotelsFromSearchResult();
        mmtSrpPage.switchToHotelDetailsPage();
        Assert.assertTrue("Hotel Details page is displayed ", mmtHotelDetailsPage.verifyHotelDetailsPageIsDisplayed());
        mmtHotelDetailsPage.switchToSrpPage(parentWindow);
        mmtSrpPage.removePriceFilter();
        Assert.assertTrue("Search results displayed after removing price filter", mmtSrpPage.verifyHotelSearchResults());
    }

    @Test(enabled = false)
    public void verifyPopularFilterSearch(){
        MmtSearchResultsPage mmtSrpPage = new MmtSearchResultsPage(driver);
        mmtSrpPage.viewMoreFilters().selectFromPopularFilterList();
        Assert.assertTrue("Search results displayed after applying popular filter", mmtSrpPage.verifyHotelSearchResults());
        mmtSrpPage.selectHotelsFromSearchResult();
    }

    @AfterClass
    public  void closeApplication(){
        closeBrowser();
    }
}
