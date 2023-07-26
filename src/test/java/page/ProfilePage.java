package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProfilePage extends AbstractPage {
    private final String HOMEPAGE_URL = "http://shop.bugred.ru/";

    private By bookingNumberTextBoxBy = By.xpath("//tr//td[1]");    

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProfilePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }
    
    public boolean isBookingRegistered(String booking) {
        List<WebElement> bookingNumbers = getAllBookingNumbers();
        for (WebElement bookingNumber : bookingNumbers) {
            if (booking.equals(bookingNumber.getText())) {
                return true;
            }
        }
        return false;
    }
    
    public List<WebElement> getAllBookingNumbers() {
        return driver.findElements(bookingNumberTextBoxBy);
    }    
}
