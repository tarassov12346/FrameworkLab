package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CorrectFilterTest extends CommonConditions{
    @Test(description = "checks the selected items to be of given color")
    public void doSelectedItemsCorrespondToFilterConditionsOfColor() {
        setNumberOfPagesForColor();
        if (numberOfPages>0){
            for (int j = 0; j < numberOfPages; j++) {
                List<WebElement> filteredElementsList = filterItemsByColorBoxTick(j);
                int numberOfFilteredElements = filteredElementsList.size();
                for (int i = 0; i < numberOfFilteredElements; i++) {
                    filteredElementsList = filterItemsByColorBoxTick(j);
                    filteredElementsList.get(i).click();
                    Assert.assertTrue(getItemColoursAsString().contains(String.format("background-color: rgb(%s);", CHECK_COLOUR)));
                }
            }
        }

    }

    @Test(description = "checks the selected items to be of given price rank")
    public void doSelectedItemsCorrespondToFilterConditionsOfPriceRank() {
        setNumberOfPagesForPriceRank(LOWER_PRICE, UPPER_PRICE);
        if (numberOfPages>0){
            for (int j = 0; j < numberOfPages; j++) {
                List<WebElement> filteredElementsList = filterItemsByGivenPriceRank(LOWER_PRICE, UPPER_PRICE,j);
                int numberOfFilteredElements = filteredElementsList.size();

                for (int i = 0; i < numberOfFilteredElements; i++) {
                    filteredElementsList = filterItemsByGivenPriceRank(LOWER_PRICE, UPPER_PRICE,j);
                    filteredElementsList.get(i).click();
                    Assert.assertTrue((getItemPrice() >= LOWER_PRICE_DOUBLE_VALUE) && (getItemPrice() <= UPPER_PRICE_DOUBLE_VALUE));

                }
            }
        }

    }
}
