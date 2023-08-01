package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.RegistrationPage;
import static utils.CustomExceptions.*;
import static utils.RegistrationOptions.*;

public class RegistrationPageTest extends CommonConditions {
    RegistrationPage registrationPage;
    Logger logger = LogManager.getRootLogger();

    @BeforeMethod(description = "Initializes and opens registration page")
    public void initialPage() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        logger.info("Registration page has been opened for " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @BeforeClass(description = "Preregisters user from bundle before running tests. If it already exist handles an exception")
    public void registerUserFromBundle() {
        initialPage();
        try {
            registrationPage.registerUser();
        } catch (UserAlreadyRegisteredException e) {
            logger.info("User from bundle already registered");
        }
    }

    @Test(description = "Registration of user from bundle ")
    public void preregisteredUserFromBundleRegistrationTest() {
        logger.info("[preregisteredUserFromBundleRegistrationTest] : has been started");
        Assert.assertThrows(UserAlreadyRegisteredException.class, () -> registrationPage
                .registerUser());
    }

    @Test(description = "Random user registration test")
    public void randomUserRegistrationTest() {
        logger.info("[randomUserRegistrationTest] : has been started");
        Assert.assertTrue(registrationPage
                .registerUser(DEFAULT)
                .successfulMessageAppear());
    }

    @Test(description = "Register user with blank name field")
    public void registerUserWithBlankNameTest() {
        logger.info("[registerUserWithBlankNameTest] : has been started");
        Assert.assertThrows(InvalidNameException.class, () -> registrationPage
                .registerUser(NAME));
    }

}
