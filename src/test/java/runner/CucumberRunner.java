package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
        glue = "step", tags = "@Login")

public class CucumberRunner extends AbstractTestNGCucumberTests {

}