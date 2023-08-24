package step;

import driver.DriverSingleton;
import page.ShopPage;
import page.CartPage;
import page.HeaderPage;
import page.ProfilePage;
import service.PageService;
import org.openqa.selenium.WebDriver;

public class CucumberTestEnvironment {
    protected WebDriver driver = DriverSingleton.getDriver();;
    protected ShopPage shopPage = new ShopPage(driver);
    protected CartPage cartPage = new CartPage(driver);
    protected HeaderPage headerPage = new HeaderPage(driver);
    protected ProfilePage profilePage = new ProfilePage(driver);
    protected PageService pageService = new PageService(driver);    
}
