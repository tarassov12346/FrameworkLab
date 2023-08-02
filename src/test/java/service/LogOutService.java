package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.RegistrationPage;

import java.time.Duration;

public class LogOutService {
    WebDriver driver;
    Logger logger = LogManager.getRootLogger();
    @FindBy(id = "navbarDropdown2")
    private WebElement userButton;
    @FindBy(xpath = "//a[@href='/user/lk/logout']")
    private WebElement logOutButton;

    public LogOutService(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationPage logOut() {
        try {
            userButton.click();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return new RegistrationPage(driver);
    }

}
