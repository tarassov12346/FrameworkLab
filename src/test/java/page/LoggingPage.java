package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggingPage extends AbstractPage{
    @FindBy(id = "exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(name = "_csrf")
    private WebElement logInButton;

    @Override
    public LoggingPage openPage(String loggingPageUrl) {
        driver.get(loggingPageUrl);
        return this;
    }

    public LoggingPage fillEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }
    public LoggingPage fillPassword(String password){
        emailInput.sendKeys(password);
        return this;
    }
    public ShopPage logIn(String email){
        logInButton.click();
        return new ShopPage();
    }
}
