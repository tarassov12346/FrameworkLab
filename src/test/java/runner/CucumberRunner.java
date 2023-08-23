package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"}, features = "src/test/resources/features",
        glue = "step", tags = "@Login or @Registration")

public class CucumberRunner extends AbstractTestNGCucumberTests {

}