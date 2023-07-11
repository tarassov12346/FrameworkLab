package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShopPage  extends AbstractPage{

    private final long TIME_OUT = 40;

    @FindBy(xpath = "//*[@id='navbarSupportedContent']/form/input")
    private WebElement searchInput;
    @FindBy(xpath = "//*[@id='navbarSupportedContent']/form/button")
    private WebElement searchButton;


    @Override
    public ShopPage openPage(String homePageUrl) {
        driver.manage().window().maximize();
        driver.get(homePageUrl);
        return this;
    }

    public ShopPage enterSearchRequest(String searchRequest) {
        waitForElementVisibility(searchInput, TIME_OUT);
        searchInput.click();
        searchInput.sendKeys(searchRequest);
        return this;
    }

    public ShopPage clickSearchButton() {
        waitForElementVisibility(searchButton, TIME_OUT);
        searchButton.click();
        return this;
    }

    public List<WebElement> getSearchResults(){
        return driver.findElements(By.xpath("/html/body/div/div/div[2]/div[2]/div[1]"));
    }

    private void waitForElementVisibility(WebElement element, long secondsToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(secondsToWait)).
                until(ExpectedConditions.visibilityOf(element));
    }
}
