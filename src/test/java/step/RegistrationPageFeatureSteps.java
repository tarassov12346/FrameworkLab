package step;

import driver.DriverSingleton;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.LoginPage;
import page.RegistrationPage;
import service.UserCreator;


public class RegistrationPageFeatureSteps {
    WebDriver driver;
    RegistrationPage registrationPage;
    User user = new UserCreator(false).createUser();

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @When("user opens registration page")
    public void openRegistrationPage() {
        registrationPage = new RegistrationPage(driver).openPage();
    }

    @And("user enters name in name input")
    public void enterNameInNameInput() {
        registrationPage.fillName(user.name());
    }

    @And("user enters  email in email input")
    public void enterEmailInEmailInput() {
        registrationPage.fillEmail(user.email());
    }

    @And("user enters password in password input")
    public void enterPasswordInPasswordInput() {
        registrationPage.fillPassword(user.password());
    }

    @And("user repeats password in repeated password input")
    public void enterPasswordInRepeatedPasswordInput() {
        registrationPage.fillAffirmationPassword(user.passwordRepeated());
    }

    @And("user clicks submit registration button")
    public void userClicksSubmitRegistrationButton() {
        registrationPage.submitForm();
    }

    @Then("successful registration message {string} appears")
    public void successfulRegistrationMessageAppears(String message) {
        Assert.assertTrue(new LoginPage(driver).successfulMessageAppear());
    }
}
