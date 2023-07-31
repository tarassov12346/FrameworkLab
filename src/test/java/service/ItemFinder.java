package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.ShopPage;

import java.util.List;

public class ItemFinder {
    public ShopPage shopPage;

    public ItemFinder(WebDriver driver){
        shopPage = new ShopPage(driver);
    }

    public List<WebElement> getItems(String searchRequest,int page) {
        return shopPage.openPage().enterSearchRequest(searchRequest).
                clickSearchButton().clickPageButton(page).getSearchResults();
    }
}
