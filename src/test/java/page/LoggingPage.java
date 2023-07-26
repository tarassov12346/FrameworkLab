package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggingPage extends AbstractPage{
    @FindBy(id = "exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(name = "_csrf")
    private WebElement logInButton;

    public static final String LOGGING_PAGE_URL = "http://shop.bugred.ru/user/login/index";

    public LoggingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoggingPage openPage() {
        driver.get(LOGGING_PAGE_URL);
        return this;
    }

    public LoggingPage fillEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }
    public LoggingPage fillPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }
    public ShopPage logIn(){
        logInButton.click();
        return new ShopPage(driver);
    }
}
