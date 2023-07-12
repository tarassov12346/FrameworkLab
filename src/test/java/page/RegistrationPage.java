package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends AbstractPage{

    public static final String REGISTRATION_PAGE_URL = "http://shop.bugred.ru/user/register/index";
    public static final int EXPLICIT_WAIT = 5;
    @FindBy(id = "exampleInputName")
    private WebElement nameInput;
    @FindBy(id = "exampleInputEmail1")
    private WebElement emailInput;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordInput;
    @FindBy(id = "exampleInputPassword2")
    private WebElement passwordAffirmationInput;
    @FindBy(name = "_csrf")
    private WebElement submitButton;
    @FindBy(id = "alertify-ok")
    private WebElement alertButton;

    @Override
    public RegistrationPage openPage(String registrationPageUrl) {
        driver.get(registrationPageUrl);
        return this;
    }

    public RegistrationPage fillForm(String name, String email, String password){
        this
                .fillName(name)
                .fillEmail(email)
                .fillPassword(password)
                .fillAffirmationPassword(password);
        return this;
    }
    public RegistrationPage fillName(String name){
        waitUntilVisibilityOf(nameInput).sendKeys(name);
        return this;
    }

    public RegistrationPage fillEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }
    public RegistrationPage fillPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }
    public RegistrationPage fillAffirmationPassword(String password) {
        passwordAffirmationInput.sendKeys(password);
        return this;
    }
    public LoggingPage submitForm(){
        submitButton.click();
        waitUntilVisibilityOf(alertButton).click();
        return new LoggingPage();
    }
    private WebElement waitUntilVisibilityOf(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

}
