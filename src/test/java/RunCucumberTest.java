import driver.DriverSingleton;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features/attributes")

public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @AfterClass(description = "closes the browser")
    public void afterTestCompleted() {
        DriverSingleton.closeDriver();
    }
}
