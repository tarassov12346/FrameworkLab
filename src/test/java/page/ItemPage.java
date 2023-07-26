package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TimeoutException;

public class ItemPage extends AbstractPage {
    private final String CART_PAGE = "http://shop.bugred.ru/shop/cart/index";
    @FindBy(id = "exampleCount")
    private WebElement itemCountInputBox;
    @FindBy(xpath = "//form[contains(@action, 'cart')]//button")
    private WebElement addToCartButton;
    @FindBy(xpath = "//form[contains(@action, 'cart')]//preceding-sibling::h2")
    private WebElement itemNameTextBox;
    
    public ItemPage(WebDriver driver) {
        super(driver);
    }
    
    public ItemPage openPage() {
        driver.navigate().to(CART_PAGE);
        return this;
    }
            
    public ItemPage enterItemCount(String itemCount) {
        waitForElementVisibility(itemCountInputBox, EXPLICIT_WAIT).sendKeys(itemCount);
        return this;
    }
    
    public ItemPage clickAddToCartButton() {
        addToCartButton.click();
        return this;
    }
    
    public String getItemName() {
        return itemNameTextBox.getText();
    }
    
    public boolean isItemAvailable() {
        try {
            return waitForElementVisibility(addToCartButton, EXPLICIT_WAIT).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
