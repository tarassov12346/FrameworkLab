package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {
    private final String CART_PAGE = "http://shop.bugred.ru/shop/cart/index";
    private By itemNameTextBoxBy = By.xpath("//a[contains(@href, 'item')]");

    @FindBy(xpath = "//a[contains(@href, 'item')]//..//..//a[contains(@href, 'remove')]")
    private WebElement removeItemButton;
    @FindBy(id = "InputPhone")
    private WebElement phoneInputBox;
    @FindBy(id = "InputAddr")
    private WebElement addressInputBox;
    @FindBy(xpath = "//*[contains(text(), 'Оформить заказ')]")
    private WebElement bookButton;
    @FindBy(xpath = "//*[contains(@class, 'alert-info')]")
    private WebElement bookingTextBox;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage openPage() {
        driver.navigate().to(CART_PAGE);
        return this;
    }

    public CartPage enterPhoneNumber(String phoneNumber) {
        waitForElementToBeClickable(phoneInputBox, EXPLICIT_WAIT).sendKeys(phoneNumber);
        return this;
    }

    public CartPage enterAddress(String address) {
        waitForElementToBeClickable(addressInputBox, EXPLICIT_WAIT).sendKeys(address);
        return this;
    }

    public CartPage clickBookButton() {
        waitForElementToBeClickable(bookButton, EXPLICIT_WAIT).click();
        return this;
    }

    public boolean isItemBooked() {
        try {
            return waitForElementVisibility(bookingTextBox, EXPLICIT_WAIT).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isItemInCart(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        return itemsInCart.stream().anyMatch(item -> itemName.equals(item.getText()));
    }

    public boolean isItemRemovedFromCart(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        return itemsInCart.stream().noneMatch(item -> itemName.equals(item.getText()));
    }

    public CartPage clickRemoveItemButton(String itemName) {
        List<WebElement> itemsInCart = getAllItemsInCart();
        for (WebElement item : itemsInCart) {
            if (itemName.equals(item.getText())) {
                removeItemButton.click();
                return this;
            }
        }
        return null;
    }

    public String getBookingNumber() {
        String booking = waitForElementVisibility(bookingTextBox, EXPLICIT_WAIT).getText();
        return booking.replaceAll("[^0-9]", "");
    }

    public List<WebElement> getAllItemsInCart() {
        return driver.findElements(itemNameTextBoxBy);
    }
}
