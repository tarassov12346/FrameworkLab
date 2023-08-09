import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features/attributes")

public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
