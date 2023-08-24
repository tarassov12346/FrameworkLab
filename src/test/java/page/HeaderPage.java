package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends AbstractPage {
    private final String HOMEPAGE_URL = "http://shop.bugred.ru/";

    @FindBy(xpath = "//a[contains(@href, 'cart')]")
    private WebElement cartButton;
    @FindBy(xpath = "//*[contains(@href, 'login')]")
    private WebElement loginButton;
    @FindBy(id = "navbarDropdown2")
    private WebElement accountButton;
    @FindBy(xpath = "//a[contains(text(), 'Личный кабинет')]")
    private WebElement personalProfileButton;
    @FindBy(xpath = "//a[contains(text(), 'Выйти')]")
    private WebElement logOutButton;
    @FindBy(xpath = "//a[contains(text(), 'Главная')]")
    private WebElement mainButton;

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
    
    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }
    
    public HeaderPage clickAccountButton() {
        accountButton.click();
        return this;
    }
    
    public ProfilePage clickPersonalProfileButton() {
        personalProfileButton.click();
        return new ProfilePage(driver);
    }
    
    public LoginPage clickLogOutButton() {
        logOutButton.click();
        return new LoginPage(driver);
    }
    
    public ShopPage clickMainButton() {
 	mainButton.click();
 	return new ShopPage(driver);
    }
}
