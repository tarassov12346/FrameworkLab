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
import service.UserCreator;

public class LoginPageFeatureSteps{

    LoginPage loginPage;
    WebDriver driver;
    User user = new UserCreator(true).createUser();

    @Before
    void SetUp(){
        driver = DriverSingleton.getDriver();
    }
    @When("user opens login page")
    public void openLoginPage() {

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
    public void clicksSubmitLoginButton() {
        loginPage.logIn();
    }

    @Then("a confirmation message with the text {string} appears")
    public void confirmationMessageAppears() {
        Assert.assertTrue(loginPage.successfulMessageAppear());
    }
}
