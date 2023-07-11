package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import service.ItemFinder;
import service.SearchRequestReader;
import utils.TestListener;

import java.util.List;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static final String HOMEPAGE_URL = "http://shop.bugred.ru/";
    protected static final String SEARCH_REQUEST_ONE = "search.request.one";
    protected static final String SEARCH_REQUEST_TWO = "search.request.two";
    protected static final String SEARCH_REQUEST_THREE = "search.request.three";


    public List<WebElement> getItemFinder(String searchRequest) {
        return getItemForStart(searchRequest);
    }

    private List<WebElement> getItemForStart(String searchRequest) {
        return new ItemFinder().getItems(HOMEPAGE_URL, SearchRequestReader.getSearchData(searchRequest));
    }

    @AfterTest(description = "closes the browser")
    public void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
}
