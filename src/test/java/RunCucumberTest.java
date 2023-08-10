import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"},
        features = "src/test/resources/features/attributes")

public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
