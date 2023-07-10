package test;

import driver.DriverSingleton;
import org.testng.annotations.*;
import service.ItemFinder;
import utils.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static final String HOMEPAGE_URL = "http://shop.bugred.ru/";
    protected static final String SEARCH_REQUEST = "Шорты";

    ItemFinder itemFinder = getItemForStart();



    private ItemFinder getItemForStart() {
        return new ItemFinder().getItem(HOMEPAGE_URL,SEARCH_REQUEST);
    }



    @AfterTest(description = "closes the browser")
    public void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
}
