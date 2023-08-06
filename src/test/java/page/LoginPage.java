package page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    @FindBy(id = "exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(id = "exampleInputPassword2")
    private WebElement passwordAffirmationInput;
    @FindBy(name = "_csrf")
    private WebElement logInButton;
    @FindBy(id = "alertify-ok")
    private WebElement alertButton;

    private static final String LOGGING_PAGE_URL = "http://shop.bugred.ru/user/login/index";

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

    /**
     * Fills input forms and submits
     *
     * @param user Takes user whom to try login
     * @return ShopPage instance if logged in
     */
    public ShopPage fillFieldsAndLoginUser(User user) {
        this.fillEmail(user.email())
                .fillPassword(user.password())
                .logIn();
        if (driver.getCurrentUrl().equals("http://shop.bugred.ru/")) {
            return new ShopPage(driver);
        } else return null;
    }

    public Boolean successfulMessageAppear() {
        waitForElementVisibility(alertButton);
        return checkIfElementExistByLinkText("Теперь вы можете войти используя свой email и пароль!");
    }
}
