package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ShopPage extends AbstractPage {

    private final long TIME_OUT = 40;
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


    private final String COLOUR_SELECT = "//input[@value='%s'][@name='colors[]']";


    public ShopPage(WebDriver driver) {
        super(driver);
        logger.info("Page initialized");
    }

    @Override
    public ShopPage openPage() {
        driver.get(HOMEPAGE_URL);
        PageFactory.initElements(this.driver, this);
        logger.info("Page loaded");
        return this;
    }

    public ItemPage clickItem() {
        waitForElementVisibility(firstItemButton, EXPLICIT_WAIT).click();
        return new ItemPage(driver);
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

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//a[contains(@href, 'item')]"));
    }

    public int getPagesButtonsNumber() {
        return getPagesButtons().size();
    }

    public List<WebElement> getPagesButtons() {
        return driver.findElements(By.xpath("//div[2]/div[2]/div[2]/nav/ul/*"));
    }

    public ShopPage clickPageButton(int value) {
        getPagesButtons().get(value).click();
        return this;
    }

    public ShopPage checkBoxColour(int value) {
        checkBoxHandling(driver.findElement(By.xpath(String.format(COLOUR_SELECT, value))));
        return this;
    }

    public ShopPage checkBox42Size() {
        checkBoxHandling(checkBox42Size);
        return this;
    }

    public ShopPage enterLowerPrice(String lowerPrice) {
        priceFromBox.click();
        priceFromBox.sendKeys(lowerPrice);
        return this;
    }

    public ShopPage enterUpperPrice(String upperPrice) {
        priceToBox.click();
        priceToBox.sendKeys(upperPrice);
        return this;
    }

    public ShopPage clickSearchLowerButton() {
        waitForElementVisibility(searchLowerButton, TIME_OUT);
        searchLowerButton.click();
        return this;
    }

    public String getItemColoursAsString() {
        waitForElementVisibility(itemColourIcon, TIME_OUT);
        List<WebElement> webElementList = driver.findElements(By.xpath("//div[2]/p[1]/*"));
        List<String> styleList = new ArrayList<>();
        webElementList.stream().forEach(webElement -> {
            styleList.add(webElement.getAttribute("style"));
        });
        return String.join(",", styleList);
    }

    public String getItemSize() {
        return itemSizeIcon.getText();
    }

    public Double getItemPrice() {
        String[] priceTextArray = itemPriceIcon.getText().trim().split(" ");
        return Double.parseDouble(priceTextArray[0]);
    }

    private void checkBoxHandling(WebElement checkBoxField) {
        waitForElementVisibility(checkBoxField, TIME_OUT);
        checkBoxField.click();
    }
}
