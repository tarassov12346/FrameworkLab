package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(id = "exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(name = "_csrf")
    private WebElement logInButton;
    @FindBy(id = "alertify-ok")
    private WebElement alertButton;

    public static final String LOGGING_PAGE_URL = "http://shop.bugred.ru/user/login/index";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage openPage() {
        driver.get(LOGGING_PAGE_URL);
        return this;
    }

    public LoginPage fillEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public ShopPage logIn() {
        logInButton.click();
        return new ShopPage(driver);
    }

    public Boolean successfulMessageAppear() {
        waitForElementVisibility(alertButton);
        return checkIfElementExistByLinkText("Теперь вы можете войти используя свой email и пароль!");
    }
}
