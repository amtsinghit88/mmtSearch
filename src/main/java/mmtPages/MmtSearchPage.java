package mmtPages;

import JavaUtils.JavaUtils;
import baseSetUp.BrowserSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import seleniumUtils.SeleniumUtil;

import java.time.LocalDate;
import java.util.List;

public class MmtSearchPage extends BrowserSetUp {

    public  WebElement getHotelsTab(){
        return driver.findElement(By.xpath("//span[text()= 'Hotels']"));
    }

    public  WebElement getMmtLogo(){
        return  driver.findElement(By.xpath("//a[@data-cy= 'mmtLogo']"));
    }

    public WebElement getCheckInBox(){
        return  driver.findElement(By.xpath("//label[@for= 'checkin']"));
    }

    public WebElement getCheckOutBox(){
        return  driver.findElement(By.xpath("//label[@for= 'checkout']"));
    }

    public WebElement getMonthElement(){
        return driver.findElement(By.xpath("(//div[@class = 'DayPicker-Month']/div/div)[1]"));
    }

    public  WebElement getBackArrowIcon(){
        return  driver.findElement(By.xpath("//span[@aria-label= 'Previous Month']"));
    }
    public  List<WebElement> getCheckInDateElement(){
        return  driver.findElements(By.xpath("//div[@class= 'DayPicker-Day']"));
    }

    public List<WebElement> getCheckOutDateElement(){
        return  driver.findElements(By.xpath("//div[@class= 'DayPicker-Day']"));
    }

    public WebElement getRoomGuestSelectBox(){
        return driver.findElement(By.xpath("//div[@class = 'hsw_inputBox roomGuests  ']"));
    }

    public WebElement getSelectedGuests(){
        return  driver.findElement(By.xpath("//label[@for= 'guest']"));
    }


    public WebElement getSelectAdultElement(String noOfAdults){
       return  driver.findElement(By.xpath("//li[@data-cy= 'adults-"+noOfAdults+"']"));
    }

    public WebElement getSelectChildElement(String noOfChild){
        return driver.findElement(By.xpath("//li[@data-cy= 'children-"+noOfChild+"']"));
    }

    public WebElement getChildAgeSelectBox(){
        return driver.findElement(By.xpath("//select[@class= 'ageSelectBox']"));
    }

    public  WebElement getApplyButton(){
        return driver.findElement(By.xpath("//button[text() = 'APPLY']"));
    }

    public  WebElement getSearchButton(){
        return  driver.findElement(By.id("hsw_search_button"));
    }

    public  MmtSearchPage(WebDriver driver){
        this.driver = driver;
    }

    public MmtSearchPage clickOnMmtLogo()
    {
        Actions userAction = new Actions(driver);
        userAction.moveToElement(getMmtLogo()).click().release().perform();
        return  this;
    }
    public MmtSearchPage selectHotelTab(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", getHotelsTab());
        return this;
    }

    public void selectCheckInCheckOutDate(int noOfDays){
        JavaUtils utils = new JavaUtils();
        LocalDate checkInDate = utils.getFutureDate(1);
        String[] date = utils.dateSplitter(checkInDate);
        LocalDate checkOutDate = utils.getFutureDate(noOfDays);
        SeleniumUtil.clickOnElement(driver,getCheckInBox());
        boolean isPreviousMonthIconDisplayed = getBackArrowIcon().isDisplayed();
        while (isPreviousMonthIconDisplayed){
            SeleniumUtil.clickOnElement(driver,getBackArrowIcon());
            isPreviousMonthIconDisplayed = getBackArrowIcon().isDisplayed();
        }
        SeleniumUtil.clickGivenDay(getCheckInDateElement(), date[2]);
        System.out.println("Selected CheckIn Date is "+ SeleniumUtil.getElementText(driver,getCheckInBox()));
        SeleniumUtil.clickGivenDay(getCheckOutDateElement(),utils.dateSplitter(checkOutDate)[2]);
        System.out.println("Selected CheckIn Date is "+ SeleniumUtil.getElementText(driver,getCheckOutBox()));


    }

    public MmtSearchPage selectGuests(String noOfAdults, String noOfChild, String childAge){
        SeleniumUtil.clickOnElement(driver,getRoomGuestSelectBox());
        SeleniumUtil.clickOnElement(driver,getSelectAdultElement(noOfAdults));
        SeleniumUtil.clickOnElement(driver,getSelectChildElement(noOfChild));
        Select objSelect = new Select(getChildAgeSelectBox());
        objSelect.selectByVisibleText(childAge);
        SeleniumUtil.clickOnElement(driver,getApplyButton());
        System.out.println("Selected guests are "+SeleniumUtil.getElementText(driver,getSelectedGuests()));
        return this;
    }

    public void searchForHotels(){
        SeleniumUtil.clickOnElement(driver,getSearchButton());
    }



}
