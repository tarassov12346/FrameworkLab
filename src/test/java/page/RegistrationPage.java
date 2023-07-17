package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.UserCreator;

import java.time.Duration;

public class RegistrationPage extends AbstractPage{

    public static final String REGISTRATION_PAGE_URL = "http://shop.bugred.ru/user/register/index";
    public static final int EXPLICIT_WAIT = 5;
    protected final Logger logger = LogManager.getRootLogger();
    User testUser;
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

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public RegistrationPage openPage() {
        driver.get(REGISTRATION_PAGE_URL);
        return this;
    }

    public RegistrationPage registerUser(Boolean fromBundle){

        if (fromBundle){
            testUser = new UserCreator().createUser();
        } else {
            testUser = new UserCreator(5).createUser();
        }
        this
                .fillName(testUser.name())
                .fillEmail(testUser.email())
                .fillPassword(testUser.password())
                .fillAffirmationPassword(testUser.passwordRepeated());
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
    public AbstractPage submitForm() {
        submitButton.click();
        if (driver.findElements(By.linkText("Пользователь с таким email уже зарегистрирован!")).isEmpty()) {
            waitUntilVisibilityOf(alertButton).click();
            logger.info("Registration has been completed successfully");
            return new LoggingPage(driver);
        } else {
            // Logg or error or some logic
            waitUntilVisibilityOf(alertButton).click();
            logger.info("Registration hasn't been completed successfully");
            return this;
        }
    }
    private WebElement waitUntilVisibilityOf(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

}
