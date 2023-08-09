package attributes;

import driver.DriverSingleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.ShopPage;
import service.ItemFilter;
import service.ItemFinder;

import java.util.stream.IntStream;

public class StepDefinitionsForFilter {
    WebDriver driver;
    int numberOfPages;
    ShopPage openPage;

    @Given("I open the page thru {string}")
    public void i_open_the_page(String browser) {
        System.setProperty("browser", browser);
        driver = DriverSingleton.getDriver();
        openPage = new ItemFinder(driver).shopPage.openPage();
    }

    @When("I tick color {word} and press lower search button")
    public void i_tick_color_and_press_lower_search_button(String color) {
        numberOfPages = new ItemFilter(driver).
                shopPage.openPage().checkBoxColour(Integer.parseInt(color)).
                clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages > 1) numberOfPages -= 1;
    }

    @Then("I should see items with colors {word} corresponding to {string}")
    public void i_should_see_items_with_colors_corresponding_to(String searchColour, String checkColour) {
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, new ItemFilter(driver).filterItemsByColor(searchColour, pageIndex).size()).boxed().toList().forEach(elementIndex -> {
                    new ItemFilter(driver).filterItemsByColor(searchColour, pageIndex).get(elementIndex).click();
                    Assert.assertTrue(new ItemFilter(driver).shopPage.getItemColoursAsString().contains(String.format("background-color: rgb(%s);", checkColour)));
                });
            });
        }
    }

    @When("I tick size {word} and press lower search button")
    public void i_tick_size_and_press_lower_search_button(String size) {
        numberOfPages = new ItemFilter(driver).
                shopPage.openPage().checkBoxSize(Integer.parseInt(size)).
                clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages > 1) numberOfPages -= 1;
    }

    @Then("I should see items with sizes {word} corresponding to {word}")
    public void i_should_see_items_with_sizes_corresponding_to(String searchSize, String checkSize) {
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, new ItemFilter(driver).filterItemsBySize(searchSize, pageIndex).size()).boxed().toList().forEach(elementIndex -> {
                    new ItemFilter(driver).filterItemsBySize(searchSize, pageIndex).get(elementIndex).click();
                    Assert.assertTrue(new ItemFilter(driver).shopPage.getItemSize().contains(checkSize));
                });
            });
        }
    }

    @When("I enter price rank {word} {word} and press lower search button")
    public void i_enter_price_rank_and_press_lower_search_button(String lowerPrice, String upperPrice) {
        numberOfPages = new ItemFilter(driver).shopPage.openPage().
                enterLowerPrice(lowerPrice).
                enterUpperPrice(upperPrice).clickSearchLowerButton().getPagesButtonsNumber();
        if (numberOfPages > 1) numberOfPages -= 1;
    }

    @Then("I should see items with price rank within {word} {word}")
    public void i_should_see_items_with_price_rank_within(String lowerPrice, String upperPrice) {
        if (numberOfPages > 0) {
            IntStream.range(0, numberOfPages).boxed().toList().forEach(pageIndex -> {
                IntStream.range(0, new ItemFilter(driver).filterItemsByPrice(lowerPrice, upperPrice, pageIndex).size()).boxed().toList().forEach(elementIndex -> {
                    new ItemFilter(driver).filterItemsByPrice(lowerPrice, upperPrice, pageIndex).get(elementIndex).click();
                    Assert.assertTrue((new ItemFilter(driver).shopPage.getItemPrice() >= Double.parseDouble(lowerPrice)) && (new ItemFilter(driver).shopPage.getItemPrice() <= Double.parseDouble(upperPrice)));
                });
            });
        }
    }
}
