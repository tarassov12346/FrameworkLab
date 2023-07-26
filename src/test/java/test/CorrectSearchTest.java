package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

public class CorrectSearchTest extends CommonConditions {

    @DataProvider
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"Пальто"},
                {"Шорты"},
                {"Топик"}
        };
    }

    @Test(description = "checks the selected items correspond to search request", dataProvider = "dataProviderMethod")
    public void doSelectedItemsCorrespondToSearchRequest(String data) {
        getItemsBySearchRequest(data).
                forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                        contains(data.toLowerCase(Locale.ROOT))));
    }
}
