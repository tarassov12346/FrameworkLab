package test;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import service.UserCreator;

public class LoginPageTest extends CommonConditions{

    protected final Logger logger = LogManager.getRootLogger();


//    @BeforeClass(alwaysRun = true,
//            description = "Open login page")
//    public void openLoginPage() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.openPage();
//        logger.info("Login page opened");
//    }

    @Test(description = "Login by existing user data")
    public void loginUserFromBundleTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        logger.info("Login user from bundle test started");
        User user = new UserCreator(false).createUser();

        loginPage.fillEmail(user.email())
                .fillPassword(user.password())
                .logIn();
        Assert.assertEquals(driver.getCurrentUrl(), "http://shop.bugred.ru/");
    }

    @Test(description = "Login with wrong user data")
    public void loginWithInvalidDataTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        logger.info("Login with invalid data test started");
        User user = new UserCreator(true).createUser();
        loginPage.fillEmail(user.email())
                .fillPassword(user.password() + " ")
                .logIn();
        //Assert.assertTrue(loginPage.logInFaillureCheck());
    }

}
