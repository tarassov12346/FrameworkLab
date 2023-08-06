package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.RegistrationPage;
import utils.RegistrationOptions;

import static utils.CustomExceptions.UserAlreadyRegisteredException;
import static utils.RegistrationOptions.*;

public class RegistrationPageTest extends CommonConditions {
    RegistrationPage registrationPage;
    Logger logger = LogManager.getRootLogger();

    @BeforeClass
    public void preRegisterUserForRegistrationPageTests() {
        registerUserFromBundle();
    }

    @BeforeMethod(description = "Initializes and opens registration page")
    public void initialPage() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        logger.info("Registration page has been opened for " + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @DataProvider
    public Object[][] dataProviderMethodForInvalidDataTest() {
        return new Object[][]{
                {NAME},
                {EMAIL},
                {PASSWORD}
        };
    }

    @Test(description = "Registration of user from bundle ")
    public void preregisteredUserFromBundleRegistrationTest() {
        logger.info("[preregisteredUserFromBundleRegistrationTest] : has been started");
        Assert.assertThrows(UserAlreadyRegisteredException.class, () -> registrationPage
                .registerUser(false));
    }

    @Test(description = "Random user registration test")
    public void randomUserRegistrationTest() {
        logger.info("[randomUserRegistrationTest] : has been started");
        Assert.assertTrue(registrationPage
                .registerUser(true)
                .successfulMessageAppear());
    }

    @Test(description = "Register user with invalid data input", dataProvider = "dataProviderMethodForInvalidDataTest")
    public void registerUserWithInvalidData(RegistrationOptions option) {
        logger.info(String.format("[registerUserWithInvalid%sTest] : has been started",
                option.name().charAt(0) + option.name().substring(1).toLowerCase()));
        Assert.assertFalse(registrationPage.registerUser(option).getFieldValidation(option));
    }
}
