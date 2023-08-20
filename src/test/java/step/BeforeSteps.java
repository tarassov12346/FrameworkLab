package step;

import driver.DriverSingleton;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.HeaderPage;
import page.RegistrationPage;
import utils.CustomExceptions;


public class BeforeSteps {

    RegistrationPage registrationPage;
    WebDriver driver;
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
        } finally {
            driver.quit();
        }
    }
}
