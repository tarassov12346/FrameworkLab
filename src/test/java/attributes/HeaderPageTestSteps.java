package attributes;

import io.cucumber.java.en.When;

public class HeaderPageTestSteps extends CucumberTestEnvironment {
    @When("cart is opened")
    public void openCart() {
        headerPage.clickCartButton();
    }    
}
