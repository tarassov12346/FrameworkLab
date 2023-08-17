package attributes;

import io.cucumber.java.en.Given;

public class LoginPageTestSteps extends CucumberTestEnvironment {
    @Given("login is performed")
    public void login() {
        pageService.openHomePage()
        	.login("hello@gmail.com", "hello");
    }
}
