import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"},
        features = "src/test/resources/features/attributes", glue = "attributes")

public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
