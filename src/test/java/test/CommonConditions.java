package test;

import driver.DriverSingleton;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import service.ItemFinder;
import service.SearchRequestReader;
import page.HeaderPage;
import utils.TestListener;

import java.util.List;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static final String SEARCH_REQUEST_ONE = "search.request.one";
    protected static final String SEARCH_REQUEST_TWO = "search.request.two";
    protected static final String SEARCH_REQUEST_THREE = "search.request.three";

    public WebDriver driver;
    @BeforeClass()
    @Description("setUp() gets the driver type from the bundle")
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    public List<WebElement> getItemsBySearchRequest(String searchRequest) {
        return new ItemFinder(driver).getItems(SearchRequestReader.getSearchData(searchRequest));
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
