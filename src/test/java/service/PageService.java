package service;

import org.openqa.selenium.WebDriver;
import page.CartPage;
import page.HeaderPage;
import page.ItemPage;
import page.ShopPage;

public class PageService {
    private final WebDriver driver;
    private final String HOMEPAGE_URL = "http://shop.bugred.ru/";

    public PageService(WebDriver driver) {
        this.driver = driver;
    }

    public PageService openHomePage() {
        driver.navigate().to(HOMEPAGE_URL);
        return this;
    }

    public ItemPage addAvailableItemToCart(String itemCount) {
        new ShopPage(driver).getItemAvailableInShop()
                .enterItemCount(itemCount)
                .clickAddToCartButton();
        return new ItemPage(driver);
    }

    public PageService login(String email, String password) {
        new HeaderPage(driver).clickLoginButton()
                .fillEmail(email)
                .fillPassword(password)
                .logIn();
        return this;
    }

    public CartPage bookItem(String phone, String address) {
        return new CartPage(driver).enterPhoneNumber("12345")
                .enterAddress("221b Baker Street")
                .clickBookButton();
    }
}
