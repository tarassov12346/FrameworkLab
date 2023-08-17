package attributes;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CartPageTestSteps extends CucumberTestEnvironment {
    String item;
    String bookingNumber;

    @Given("cart has an item")
    public void cartHasItem() {
        item = pageService.openHomePage()
        	.addAvailableItemToCart("1")
        	.getItemName();
        new HeaderPageTestSteps().openCart();
    }

    @When("item is added to cart")
    public void addItemToCart() {
        item = pageService.addAvailableItemToCart("1")
        	.getItemName();
    }        

    @When("item is deleted")
    public void deleteItem() {
        cartPage.clickRemoveItemButton(item);
    }

    @When("item is booked with phone number only")
    public void bookItemWithPhoneNumber() {
        cartPage.enterPhoneNumber("12345")
        	.clickBookButton();
    }

    @When("item is booked")
    public void bookItemWithFullCredentials() {
        bookingNumber = pageService.bookItem("12345", "221b Baker Street")
        	.getBookingNumber();
    }
    
    @Then("item is visible in cart")
    public void verifyItemIsAddedToCart() {
        Assert.assertTrue(cartPage.isItemInCart(item), "Ordered item is not added to cart and not visible in cartPage");
    }
            
    @Then("item is removed from cart")
    public void verifyItemIsRemovedFromCart() {
        Assert.assertTrue(cartPage.isItemRemovedFromCart(item), "Item is not removed from cart");
    }
        
    @Then("item is not booked")
    public void verifyItemIsNotBooked() {
        Assert.assertFalse(cartPage.isItemBooked(), "Item is booked without address credentials");
    }
        
    @Then("successful booking number is displayed")
    public void verifyItemIsBooked() {
        Assert.assertTrue(cartPage.isItemBooked(), "Item is not booked");
    }        

    @Then("booked item is visible in account profile")
    public void verifyBookedItemIsVisibleInAccountProfile() {
        headerPage.clickAccountButton()
        	.clickPersonalProfileButton();
        Assert.assertTrue(profilePage.isBookingRegistered(bookingNumber), "Booking is not registered in account profile");
    }   
}
