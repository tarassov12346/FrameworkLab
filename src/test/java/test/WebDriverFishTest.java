package test;

import org.testng.annotations.Test;

public class WebDriverFishTest extends CommonConditions {

    @Test(description = "compares smth")
    public void areSmthOK() {
    itemFinder.doSmth();
    }
}
