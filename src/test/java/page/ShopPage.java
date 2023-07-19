package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class ShopPage  extends AbstractPage{

    protected static final String HOMEPAGE_URL = "http://shop.bugred.ru/";
    Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[contains(@href, 'item')]")
    private WebElement firstItemButton;

    @FindBy(xpath = "//*[@id='navbarSupportedContent']/form/input")
    private WebElement searchInput;
    @FindBy(xpath = "//*[@id='navbarSupportedContent']/form/button")
    private WebElement searchButton;

    @FindBy(xpath = "//form/p[3]/input")
    private WebElement checkBoxRedColour;
    @FindBy(xpath = "//form/p[21]/input")
    private WebElement checkBox42Size;

    @FindBy(xpath = "//form/p[33]/input")
    private WebElement priceFromBox;
    @FindBy(xpath = "//form/p[34]/input")
    private WebElement priceToBox;

    @FindBy(xpath = "//form/p[35]/button")
    private WebElement searchLowerButton;

    @FindBy(xpath = "//div[2]/p[1]/span")
    private WebElement itemColourIcon;
    @FindBy(xpath = "//div[2]/p[2]")
    private WebElement itemSizeIcon;
    @FindBy(xpath = "//*[@class='col-md-4']/*/span[@class='label label-primary']")
    private WebElement itemPriceIcon;

    public ShopPage(WebDriver driver) {
        super(driver);
        logger.info("Page initialized");
    }

    @Override
    public ShopPage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Page loaded");
        return this;
    }

    public ItemPage clickItem() {
        waitForElementVisibility(firstItemButton, EXPLICIT_WAIT).click();
        return new ItemPage(driver);
    }

    public ShopPage enterSearchRequest(String searchRequest) {
        waitForElementVisibility(searchInput, EXPLICIT_WAIT).click();
        searchInput.sendKeys(searchRequest);
        return this;
    }

    public ShopPage clickSearchButton() {
        waitForElementVisibility(searchButton, EXPLICIT_WAIT).click();
        return this;
    }

    public List<WebElement> getSearchResults(){
        return driver.findElements(By.xpath("//div[2]/div[2]/div[1]/*"));
    }
}
