package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected abstract AbstractPage openPage(String url);
}
