package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class CorrectFilterTest extends CommonConditions {

    @DataProvider
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"1", "0, 0, 0"},        //black
                {"3", "14, 82, 247"},    //blue
                {"12", "224, 124, 11"},  //green
                {"11", "20, 235, 56"},   //red
                {"2", "254, 8, 8"},      //white
                {"5", "246, 244, 244"}   //brown
        };
    }

    @Test(description = "checks the selected items to be of ticked color", dataProvider = "dataProviderMethod")
    public void doSelectedItemsCorrespondToFilterConditionsOfColor(String searchColour, String checkColour) {
        setNumberOfPagesForColor(searchColour);
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, filterItemsByColorBoxTick(pageIndex, searchColour).size()).boxed().toList().forEach(elementIndex -> {
                    filterItemsByColorBoxTick(pageIndex, searchColour).get(elementIndex).click();
                    Assert.assertTrue(getItemColoursAsString().contains(String.format("background-color: rgb(%s);", checkColour)));
                });
            });
        }
    }

    @Test(description = "checks the selected items to be of given price rank")
    public void doSelectedItemsCorrespondToFilterConditionsOfPriceRank() {
        setNumberOfPagesForPriceRank(LOWER_PRICE, UPPER_PRICE);
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, filterItemsByGivenPriceRank(LOWER_PRICE, UPPER_PRICE, pageIndex).size()).boxed().toList().forEach(elementIndex -> {
                    filterItemsByGivenPriceRank(LOWER_PRICE, UPPER_PRICE, pageIndex).get(elementIndex).click();
                    Assert.assertTrue((getItemPrice() >= LOWER_PRICE_DOUBLE_VALUE) && (getItemPrice() <= UPPER_PRICE_DOUBLE_VALUE));
                });
            });
        }
    }
}
