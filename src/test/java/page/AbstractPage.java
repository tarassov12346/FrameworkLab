package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    public static final int EXPLICIT_WAIT = 5;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected abstract AbstractPage openPage();
    
    protected WebElement waitForElementToBeClickable(WebElement element, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
        	.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElementVisibility(WebElement element, long time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.visibilityOf(element));
    }
}
