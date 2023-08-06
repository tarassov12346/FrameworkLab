package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.UserCreator;
import utils.RegistrationOptions;

import static utils.CustomExceptions.UserAlreadyRegisteredException;

public class RegistrationPage extends AbstractPage {

    private static final String REGISTRATION_PAGE_URL = "http://shop.bugred.ru/user/register/index";
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

    public LoginPage registerUser(boolean random) {
        if (random) {
            testUser = new UserCreator(false).createUser();
        } else {
            testUser = new UserCreator(true).createUser();
        }
        return fillForms();
    }

    public RegistrationPage registerUser(RegistrationOptions option) {

        switch (option) {
            case NAME -> testUser = new UserCreator(false).createUserWithBlankName();
            case EMAIL -> testUser = new UserCreator(false).creteUserWithInvalidEmail();
            case PASSWORD -> testUser = new UserCreator(false).createUserWithDifferentPasswords();
            case DEFAULT -> testUser = new UserCreator(false).createUser();
        }
        return fillFormsWithInvalidData();
    }

    public LoginPage fillForms() {
        this.fillName(testUser.name())
                .fillEmail(testUser.email())
                .fillPassword(testUser.password())
                .fillAffirmationPassword(testUser.passwordRepeated())
                .submitForm();
        return new LoginPage(driver);
    }

    public RegistrationPage fillFormsWithInvalidData() {
        this.fillName(testUser.name())
                .fillEmail(testUser.email())
                .fillPassword(testUser.password())
                .fillAffirmationPassword(testUser.passwordRepeated())
                .submitBadForm();
        return this;
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
        waitForElementVisibility(alertButton);
        if (!checkIfElementExistByLinkText("Пользователь с таким email уже зарегистрирован!")) {
            alertButton.click();
            logger.info("Registration has been completed successfully");
            return new LoginPage(driver);
        } else {
            logger.info("Problems with registration");
            throw new UserAlreadyRegisteredException("User with your properties has been already registered");
        }
    }

    public RegistrationPage submitBadForm() {
        submitButton.click();
        return this;
    }

    public boolean getFieldValidation(RegistrationOptions option) {
        return switch (option) {
            case NAME -> checkIfInputIsValid(nameInput);
            case EMAIL -> checkIfInputIsValid(emailInput);
            case PASSWORD -> checkIfInputIsValid(passwordInput) && checkIfInputIsValid(passwordAffirmationInput);
            case DEFAULT -> true;
        };
    }

}


