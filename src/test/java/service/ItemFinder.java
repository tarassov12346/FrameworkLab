package service;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page.ShopPage;

import java.util.List;

public class ItemFinder {
    public ShopPage shopPage;
    public ItemFinder(WebDriver driver){
        shopPage = new ShopPage(driver);
    }


    public List<WebElement> getItems(String homePageUrl, String searchRequest) {
        return shopPage.openPage().enterSearchRequest(searchRequest).
                clickSearchButton().getSearchResults();
    }
}
