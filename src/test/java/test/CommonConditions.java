package test;

import driver.DriverSingleton;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import service.ItemFilter;
import service.ItemFinder;
import service.SearchRequestReader;
import utils.TestListener;

import java.util.List;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static final String SEARCH_REQUEST_ONE = "search.request.one";
    protected static final String SEARCH_REQUEST_TWO = "search.request.two";
    protected static final String SEARCH_REQUEST_THREE = "search.request.three";

    protected static final String LOWER_PRICE = "lower.price";
    protected static final String UPPER_PRICE = "upper.price";

    protected static final Double LOWER_PRICE_DOUBLE_VALUE = Double.parseDouble(SearchRequestReader.getSearchData("lower.price"));
    protected static final Double UPPER_PRICE_DOUBLE_VALUE = Double.parseDouble(SearchRequestReader.getSearchData("upper.price"));


    protected static final String SEARCH_COLOUR = "search.colour";
    protected static final String CHECK_COLOUR = SearchRequestReader.getSearchData("check.colour");

    protected static final int SEARCH_COLOUR_INT_VALUE = Integer.parseInt(SearchRequestReader.getSearchData(SEARCH_COLOUR));

    int numberOfPages;

    public WebDriver driver;
    @BeforeClass()
    @Description("setUp() gets the driver type from the bundle")
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    public List<WebElement> getItemsBySearchRequest(String searchRequest) {
        return new ItemFinder(driver).getItems(SearchRequestReader.getSearchData(searchRequest));
    }

    public void setNumberOfPagesForColor(){
        numberOfPages=new ItemFilter(driver).
                shopPage.openPage().checkBoxColour(SEARCH_COLOUR_INT_VALUE).
                clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public void setNumberOfPagesForPriceRank(String lowerPrice, String upperPrice){
        numberOfPages=new ItemFilter(driver).shopPage.openPage().
                enterLowerPrice(SearchRequestReader.getSearchData(lowerPrice)).
                enterUpperPrice(SearchRequestReader.getSearchData(upperPrice)).clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages>1) numberOfPages -= 1;
    }

    public List<WebElement> filterItemsByColorBoxTick(int page){

        return new ItemFilter(driver).filterItemsByColor(SearchRequestReader.getSearchData(SEARCH_COLOUR),page);
    }

    public  List<WebElement> filterItemsByGivenPriceRank(String lowerPrice, String upperPrice, int page){

        return new ItemFilter(driver).filterItemsByPrice(SearchRequestReader.getSearchData(lowerPrice),SearchRequestReader.getSearchData(upperPrice), page);
    }

    public String getItemColoursAsString(){
        return new ItemFilter(driver).shopPage.getItemColoursAsString();
    }

    public Double getItemPrice(){
        return new ItemFilter(driver).shopPage.getItemPrice();
    }

    @AfterTest(description = "closes the browser")
    public void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
}
