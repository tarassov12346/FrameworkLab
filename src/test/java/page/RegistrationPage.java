package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.UserCreator;
import static utils.CustomExceptions.*;
import utils.RegistrationOptions;

import java.time.Duration;

public class RegistrationPage extends AbstractPage {

    public static final String REGISTRATION_PAGE_URL = "http://shop.bugred.ru/user/register/index";
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
    public LoginPage registerUser(){
        testUser = new UserCreator(true).createUser();
        return fillForms();
    }
    public LoginPage registerUser(RegistrationOptions option) {

        switch (option){
            case NAME -> testUser = new UserCreator(false).createUserWithBlankName();
            case EMAIL -> testUser = new UserCreator(false).creteUserWithInvalidEmail();
            case PASSWORD -> testUser = new UserCreator(false).createUserWithDifferentPasswords();
            case DEFAULT -> testUser = new UserCreator(false).createUser();
        }
        return fillForms();
    }

    public LoginPage fillForms() {
        this.fillName(testUser.name())
                .fillEmail(testUser.email())
                .fillPassword(testUser.password())
                .fillAffirmationPassword(testUser.passwordRepeated())
                .submitForm();
        return new LoginPage(driver);
    }
    public RegistrationPage fillName(String name) {
        waitForElementVisibility(nameInput).sendKeys(name);
        return this;
    }

    public RegistrationPage fillEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public RegistrationPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegistrationPage fillAffirmationPassword(String password) {
        passwordAffirmationInput.sendKeys(password);
        return this;
    }

    public LoginPage submitForm() {
        submitButton.click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        switch (driver.switchTo().alert().getText()){
            case "Теперь вы можете войти используя свой email и пароль!" -> {
                alertButton.click();
                logger.info("Registration has been completed successfully");
                return new LoginPage(driver);
            }

            case "Пользователь с таким email уже зарегистрирован!"-> {
                logger.info("User has been already registered");
                throw new UserAlreadyRegisteredException("User with your properties has been already registered");

            }

            case "Заполните это поле." -> {
                logger.info("Invalid name field input");
                throw new InvalidNameException("Invalid name input");

            }
            case "Пароль и повтор пароля не равны!" -> {
                logger.info("Password mismatch");
                throw new PasswordMismatchException("Password mismatch");
            }
            default -> {
                logger.info("Invalid email input");
                throw new InvalidEmailException("Invalid email input");
            }}
    }
//        waitForElementVisibility(alertButton);
//        if (!checkIfElementExistByLinkText("Пользователь с таким email уже зарегистрирован!")) {
//            alertButton.click();
//            logger.info("Registration has been completed successfully");
//            return new LoginPage(driver);
//        } else {
//            logger.info("Problems with registration");
//            throw new UserAlreadyRegisteredException("User with your properties has been already registered");
//        }


   }

