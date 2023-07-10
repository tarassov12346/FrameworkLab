package page;

public class ShopPage  extends AbstractPage{
    @Override
    public ShopPage openPage(String homePageUrl) {
        driver.manage().window().maximize();
        driver.get(homePageUrl);
        return this;
    }
}
