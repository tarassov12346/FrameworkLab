package step;

import driver.DriverSingleton;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.LoginPage;
import page.ShopPage;
import service.UserCreator;

public class LoginPageFeatureSteps {


    WebDriver driver;
    LoginPage loginPage;
    ShopPage shopPage;
    User user = new UserCreator(true).createUser();
    @Before
    public void setUp(){
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
    }
    @AfterAll
    void tearDown(){
        DriverSingleton.closeDriver();
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
    public void clicksSubmitLoginButton() throws InterruptedException {
        loginPage.logIn();
    }

    @Then("header menu contains button with logged in user name")
    public void confirmationMessageAppears() {
        Assert.assertEquals(user.name(), new ShopPage(driver).getUserName());
    }
}
