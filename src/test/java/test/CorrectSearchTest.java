package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.stream.IntStream;

public class CorrectSearchTest extends CommonConditions {

    @DataProvider
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"Пальто"},
                {"Шорты"},
                {"Товар"}
        };
    }

    @Test(description = "checks the selected items correspond to search request", dataProvider = "dataProviderMethod")
    public void doSelectedItemsCorrespondToSearchRequest(String data) {
        setNumberOfPagesForSearchRequest(data);
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                getItemsBySearchRequest(pageIndex, data).
                        forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                                contains(data.toLowerCase(Locale.ROOT))));
            });
        }
    }
}
