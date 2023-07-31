package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.ShopPage;

import java.util.List;


public class ItemFilter {
    public ShopPage shopPage;

    public ItemFilter(WebDriver driver) {
        shopPage = new ShopPage(driver);
    }

    public List<WebElement> filterItemsByColor(String value, int page) {
        return shopPage.openPage().checkBoxColour(Integer.parseInt(value)).clickSearchLowerButton()
                .clickPageButton(page).getSearchResults();
    }

    public List<WebElement> filterItemsBySize(String value, int page) {
        return shopPage.openPage().checkBoxSize(Integer.parseInt(value)).clickSearchLowerButton()
                .clickPageButton(page).getSearchResults();
    }

    public List<WebElement> filterItemsByPrice(String lowerPrice, String upperPrice, int page) {
        return shopPage.openPage().enterLowerPrice(lowerPrice).
                enterUpperPrice(upperPrice).clickSearchLowerButton().clickPageButton(page)
                .getSearchResults();
    }
}
