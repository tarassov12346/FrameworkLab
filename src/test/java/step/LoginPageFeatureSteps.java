package step;

import driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.HeaderPage;
import page.LoginPage;
import page.RegistrationPage;
import page.ShopPage;
import service.UserCreator;
import utils.CustomExceptions;

public class LoginPageFeatureSteps {
    WebDriver driver;
    LoginPage loginPage;
    ShopPage shopPage;
    User user = new UserCreator(true).createUser();
    RegistrationPage registrationPage;
    Logger logger = LogManager.getRootLogger();

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @Given("Open the main page")
    public void openTheMainPage() {
        new HeaderPage(driver).openPage();
    }

    @And("I have already registered")
    public void preRegisterUserFromBundle() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        try {
            registrationPage.registerUser(false);
        } catch (CustomExceptions.UserAlreadyRegisteredException e) {
            logger.info("User from bundle already registered");
        }
    }

    @When("user opens login page")
    public void openLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @And("user enters registered email in email input")
    public void enterRegisteredEmailInEmailInput() {
        loginPage.fillEmail(user.email());
    }

    @And("user enters valid password in password input")
    public void entersValidPasswordInPasswordInput() {
        loginPage.fillPassword(user.password());
    }

    @And("user clicks submit login button")
    public void clicksSubmitLoginButton() throws InterruptedException {
        loginPage.logIn();
    }

    @Then("header menu contains button with logged in user name")
    public void confirmationMessageAppears() {
        Assert.assertEquals(user.name(), new ShopPage(driver).getUserName());
    }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
