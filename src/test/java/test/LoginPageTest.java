package test;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import service.UserCreator;

public class LoginPageTest extends CommonConditions {

    protected final Logger logger = LogManager.getRootLogger();
    LoginPage loginPage;

    @BeforeClass
    public void preRegisterUserForLoginPageTests() {
        registerUserFromBundle();
    }

    @BeforeMethod(description = "Initializes and opens registration page")
    public void initialPage() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
    }

    @Test(description = "Login by existing user data")
    public void loginUserFromBundleTest() {
        logger.info("[loginUserFromBundleTest] : has been started");

        User user = new UserCreator(true).createUser();
        Assert.assertNotNull(loginPage.fillFieldsAndLoginUser(user));
    }

    @Test(description = "Login unregistered user")
    public void loginUnRegisteredUser() {
        logger.info("[loginUserWithInvalid%sTest] : has been started");

        User user = new UserCreator(false).createUser();
        Assert.assertNull(loginPage.fillFieldsAndLoginUser(user));
    }

}
