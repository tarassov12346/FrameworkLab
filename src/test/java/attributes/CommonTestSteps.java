package attributes;

import io.cucumber.java.en.Given;

public class CommonTestSteps extends CucumberTestEnvironment {
    @Given("main page is open")
    public void openMainPage() {
        pageService.openHomePage();
    }
}
