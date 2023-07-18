package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends AbstractPage {
    private final String HOMEPAGE_URL = "http://shop.bugred.ru/";
    @FindBy(xpath = "//a[contains(@href, 'cart')]")
    private WebElement cartButton;

    public HeaderPage(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public HeaderPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }
    
    public CartPage clickCartButton() {
        cartButton.click();
        return new CartPage(driver);
    }    
}
