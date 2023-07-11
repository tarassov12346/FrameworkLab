package service;

import org.openqa.selenium.WebElement;
import page.ShopPage;

import java.util.List;

public class ItemFinder {

    public ShopPage shopPage = new ShopPage();

    public List<WebElement> getItems(String homePageUrl, String searchRequest) {
        return shopPage.openPage(homePageUrl).enterSearchRequest(searchRequest).
                clickSearchButton().getSearchResults();
    }
}
