package test;

import com.fasterxml.jackson.databind.SerializerProvider;
import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.SearchRequestReader;

public class CorrectSearchTest extends CommonConditions {
    @Test(description = "compares the search request and the selected items")
    public void doSelectedItemsCorrespondToSearchRequestOne() {
    getItemFinder(SEARCH_REQUEST_ONE).stream().
            forEach(item -> Assert.assertTrue(item.getText().
                    contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_ONE))));
    }

    @Test(description = "compares the search request and the selected items")
    public void doSelectedItemsCorrespondToSearchRequestTwo() {
        getItemFinder(SEARCH_REQUEST_TWO).stream().
                forEach(item -> Assert.assertTrue(item.getText().
                        contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_TWO))));
    }

    @Test(description = "compares the search request and the selected items")
    public void doSelectedItemsCorrespondToSearchRequestThree() {
        getItemFinder(SEARCH_REQUEST_THREE).stream().
                forEach(item -> Assert.assertTrue(item.getText().
                        contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_THREE))));
    }




}
