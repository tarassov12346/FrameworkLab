package attributes;

import driver.DriverSingleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.ShopPage;
import service.ItemFinder;

import java.util.Locale;
import java.util.stream.IntStream;

public class StepDefinitionsForSearch {
    WebDriver driver;
    int numberOfPages;
    ShopPage openPage;

    @Given("I am on the page thru {string}")
    public void i_am_on_the_page(String browser) {
        System.setProperty("browser", browser);
        driver = DriverSingleton.getDriver();
        openPage = new ItemFinder(driver).shopPage.openPage();
    }

    @When("I enter {word} and press search button")
    public void i_enter_search_request_and_press_search_button(String searchRequest) {
        numberOfPages = openPage.enterSearchRequest(searchRequest).
                clickSearchButton().getPagesButtonsNumber();
        if (numberOfPages > 1) numberOfPages -= 1;
    }

    @Then("I should see items with names corresponding to {word}")
    public void i_should_see_items_with_names_corresponding_to_search_request(String searchRequest) {
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                new ItemFinder(driver).getItems(searchRequest, pageIndex).
                        forEach(item -> Assert.assertTrue(item.getText().toLowerCase(Locale.ROOT).
                                contains(searchRequest.toLowerCase(Locale.ROOT))));
            });
        }
    }
}
