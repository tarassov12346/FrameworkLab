package test;

import driver.DriverSingleton;
import org.testng.annotations.Test;
import page.RegistrationPage;

public class RegistrationPageTest extends CommonConditions {
    @Test(description = "Registration of user from bundle ")
    public void userFromBundleRegistrationTest() {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.openPage()
                .registerUser(true)
                .submitForm();
    }

    @Test(description = "Random user registration test")
    public void randomUserRegistrationTest()  {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.openPage()
                .registerUser(false)
                .submitForm();
    }
}
