package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class CorrectFilterTest extends CommonConditions {
    @Test(description = "checks the selected items to be of given color")
    public void doSelectedItemsCorrespondToFilterConditionsOfColor() {
        setNumberOfPagesForColor();
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, filterItemsByColorBoxTick(pageIndex).size()).boxed().toList().forEach(elementIndex -> {
                    filterItemsByColorBoxTick(pageIndex).get(elementIndex).click();
                    Assert.assertTrue(getItemColoursAsString().contains(String.format("background-color: rgb(%s);", CHECK_COLOUR)));
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
