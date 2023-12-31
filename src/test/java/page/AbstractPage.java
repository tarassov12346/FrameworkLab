package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.LogOutService;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final int EXPLICIT_WAIT = 5;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected abstract AbstractPage openPage();

    protected WebElement waitForElementToBeClickable(WebElement element, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element to be visible on the page for 5 seconds
     *
     * @param element Parameter assumes WebElement type
     * @return WebElement for the parameter
     */
    protected WebElement waitForElementVisibility(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementVisibility(WebElement element, long time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Checks for element existence on the page by its link text
     *
     * @param expression Parameter assumes string text of the link
     * @return True if element exist and false over-wise
     */
    public boolean checkIfElementExistByLinkText(String expression) {
        return !(driver.findElements(By.xpath(String.format("//p[contains(text(),'%s')]", expression))).isEmpty());
    }

    /**
     * Checks that the data entered in the input field complies with the requirements
     *
     * @param element Parameter assumes WebElement of input field
     * @return True if valid and false over-wise
     */
    protected boolean checkIfInputIsValid(WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].validity.valid;", element);
    }

    protected RegistrationPage logOut() {
        return new LogOutService(driver).logOut();
    }
}
