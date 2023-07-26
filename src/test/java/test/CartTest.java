package test;

import page.ShopPage;
import page.CartPage;
import page.HeaderPage;
import page.ProfilePage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class CartTest extends CommonConditions {
    @Test
    public void verifyItemAddedToCartAppearInCartPage() {
        String itemAddedToCart = new ShopPage(driver).openPage()
		.getItemAvailableInShop()
        	.enterItemCount("1")
        	.clickAddToCartButton()
        	.getItemName();        
        CartPage cartPage = new HeaderPage(driver).clickCartButton();
        Assert.assertTrue(cartPage.isItemInCart(itemAddedToCart), 
        	"Ordered item is not added to cart and not visible in cartPage");
    }
    
    @Test
    public void verifyDeleteButtonRemovesItemFromCart() {
        String itemAddedToCart = new ShopPage(driver).openPage()
        	.getItemAvailableInShop()
        	.enterItemCount("1")
        	.clickAddToCartButton()
        	.getItemName();
        CartPage cartPage = new HeaderPage(driver).clickCartButton()
        	.clickRemoveItemButton(itemAddedToCart);
        Assert.assertTrue(cartPage.isItemRemovedFromCart(itemAddedToCart), "Item is not removed from cart");
    }
    
    @Test
    public void verifyItemNotBookedWithoutAddress() {
       new ShopPage(driver).openPage()
       	.getItemAvailableInShop()
       	.enterItemCount("1")
       	.clickAddToCartButton();
       CartPage cartPage = new HeaderPage(driver).clickCartButton()
       	.enterPhoneNumber("12345")
       	.clickBookButton();
       Assert.assertFalse(cartPage.isItemBooked(), "Item is booked without address credentials");
    }
    
    @Test
    public void verifyItemBookedWithPhoneAndAddress() {
        new ShopPage(driver).openPage()
        	.getItemAvailableInShop()
        	.enterItemCount("1")
        	.clickAddToCartButton();
        CartPage cartPage = new HeaderPage(driver).clickCartButton()
        	.enterPhoneNumber("12345")
        	.enterAddress("221b Baker Street")
        	.clickBookButton();
        Assert.assertTrue(cartPage.isItemBooked(), "Item is not booked");        	
    }
    
    @Test(groups = {"logOut"})
    public void verifyBookedItemsStoredInAccountProfile() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.openPage()
        	.clickLoginButton()
        	.fillEmail("hello@gmail.com")
        	.fillPassword("hello")
        	.logIn()
        	.getItemAvailableInShop()
        	.enterItemCount("1")
        	.clickAddToCartButton();
        String bookingNumber = headerPage.clickCartButton()
        	.enterPhoneNumber("12345")
        	.enterAddress("221b Baker Street")
        	.clickBookButton()
        	.getBookingNumber();
        ProfilePage profilePage = headerPage.clickAccountButton()
        	.clickPersonalProfileButton();
        Assert.assertTrue(profilePage.isBookingRegistered(bookingNumber), "Booking is not registered in account profile");
    }
}
