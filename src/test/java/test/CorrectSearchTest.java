package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import service.SearchRequestReader;

import java.util.Locale;

public class CorrectSearchTest extends CommonConditions {
    @Test(description = "compares the search request one and the selected items names")
    public void doSelectedItemsCorrespondToSearchRequestOne() {
        getItemsBySearchRequest(SEARCH_REQUEST_ONE).stream().
                forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                        contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_ONE).toLowerCase(Locale.ROOT))));
    }

    @Test(description = "compares the search request two and the selected items names")
    public void doSelectedItemsCorrespondToSearchRequestTwo() {
        getItemsBySearchRequest(SEARCH_REQUEST_TWO).stream().
                forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                        contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_TWO).toLowerCase(Locale.ROOT))));
    }

    @Test(description = "compares the search request three and the selected items names")
    public void doSelectedItemsCorrespondToSearchRequestThree() {
        getItemsBySearchRequest(SEARCH_REQUEST_THREE).stream().
                forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                        contains(SearchRequestReader.getSearchData(SEARCH_REQUEST_THREE).toLowerCase(Locale.ROOT))));
    }




}
