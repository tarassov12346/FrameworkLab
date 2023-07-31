package test;

import driver.DriverSingleton;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import service.ItemFilter;
import service.ItemFinder;
import service.SearchRequestReader;
import page.HeaderPage;
import utils.TestListener;

import java.util.List;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static final String LOWER_PRICE = "lower.price";
    protected static final String UPPER_PRICE = "upper.price";

    protected static final Double LOWER_PRICE_DOUBLE_VALUE = Double.parseDouble(SearchRequestReader.getSearchData("lower.price"));
    protected static final Double UPPER_PRICE_DOUBLE_VALUE = Double.parseDouble(SearchRequestReader.getSearchData("upper.price"));

    int numberOfPages;

    public WebDriver driver;
    @BeforeClass()
    @Description("setUp() gets the driver type from the bundle")
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    public void setNumberOfPagesForSearchRequest(String searchRequest){
        numberOfPages=new ItemFinder(driver).
                shopPage.openPage().enterSearchRequest(searchRequest).
                clickSearchButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public List<WebElement> getItemsBySearchRequest(int page,String searchRequest) {
        return new ItemFinder(driver).getItems(searchRequest,page);
    }

    public void setNumberOfPagesForColor(String searchColour){
        numberOfPages=new ItemFilter(driver).
                shopPage.openPage().checkBoxColour(Integer.parseInt(searchColour)).
                clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public void setNumberOfPagesForSize(String searchSize){
        numberOfPages=new ItemFilter(driver).
                shopPage.openPage().checkBoxSize(Integer.parseInt(searchSize)).
                clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public void setNumberOfPagesForPriceRank(String lowerPrice, String upperPrice){
        numberOfPages=new ItemFilter(driver).shopPage.openPage().
                enterLowerPrice(SearchRequestReader.getSearchData(lowerPrice)).
                enterUpperPrice(SearchRequestReader.getSearchData(upperPrice)).clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public List<WebElement> filterItemsByColorBoxTick(int page, String searchColour){

        return new ItemFilter(driver).filterItemsByColor(searchColour,page);
    }

    public List<WebElement> filterItemsBySizeBoxTick(int page, String searchColour){

        return new ItemFilter(driver).filterItemsBySize(searchColour,page);
    }


    public  List<WebElement> filterItemsByGivenPriceRank(String lowerPrice, String upperPrice, int page){

        return new ItemFilter(driver).filterItemsByPrice(SearchRequestReader.getSearchData(lowerPrice),SearchRequestReader.getSearchData(upperPrice), page);
    }

    public String getItemColoursAsString(){
        return new ItemFilter(driver).shopPage.getItemColoursAsString();
    }

    public String getItemSize(){
        return new ItemFilter(driver).shopPage.getItemSize();
    }

    public Double getItemPrice(){
        return new ItemFilter(driver).shopPage.getItemPrice();
    }

    @AfterTest(description = "closes the browser")
    public void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
    
    @AfterMethod(onlyForGroups = {"logOut"})
    public void logOut() {
        new HeaderPage(driver).clickAccountButton()
        	.clickLogOutButton();
    }
}
