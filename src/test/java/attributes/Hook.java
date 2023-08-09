package attributes;

import driver.DriverSingleton;
import io.cucumber.java.AfterAll;

public class Hook {
    @AfterAll
    public static void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
}
