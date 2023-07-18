package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends AbstractPage {
    private final String CART_PAGE = "http://shop.bugred.ru/shop/cart/index";
    private By itemNameTextBoxBy = By.xpath("//a[contains(@href, 'item')]");
    
    @FindBy(xpath = "//a[contains(@href, 'item')]//..//..//a[contains(@href, 'remove')]")
    private WebElement removeItemButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public CartPage openPage() {
        driver.navigate().to(CART_PAGE);
        return this;
    }
    
    public boolean isItemInCart(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        for(WebElement item : itemsInCart) {
            if (itemName.equals(item.getText())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isItemRemovedFromCart(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        for(WebElement item : itemsInCart) {
            if (itemName.equals(item.getText())) {
                return false;
            }
        }
        return true;
    }
    
    public CartPage clickRemoveItemButton(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        for(WebElement item : itemsInCart) {
            if (itemName.equals(item.getText())) {
                removeItemButton.click();
                return this;
            }
        }
        return null;
    }
    
    public List<WebElement> getAllItemsInCart() {
        return driver.findElements(itemNameTextBoxBy);
    }
}
