package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CartPage;
import page.HeaderPage;
import page.ProfilePage;
import service.PageService;

public class CartTest extends CommonConditions {
    @Test
    public void verifyItemAddedToCartAppearInCartPage() {
        String itemAddedToCart = new PageService(driver).openHomePage()
                .addAvailableItemToCart("1")
                .getItemName();
        CartPage cartPage = new HeaderPage(driver).clickCartButton();
        Assert.assertTrue(cartPage.isItemInCart(itemAddedToCart),
                "Ordered item is not added to cart and not visible in cartPage");
    }

    @Test
    public void verifyDeleteButtonRemovesItemFromCart() {
        String itemAddedToCart = new PageService(driver).openHomePage()
                .addAvailableItemToCart("1")
                .getItemName();
        CartPage cartPage = new HeaderPage(driver).clickCartButton()
                .clickRemoveItemButton(itemAddedToCart);
        Assert.assertTrue(cartPage.isItemRemovedFromCart(itemAddedToCart), "Item is not removed from cart");
    }

    @Test
    public void verifyItemIsNotBookedWithoutAddress() {
        new PageService(driver).openHomePage()
                .addAvailableItemToCart("1");
        CartPage cartPage = new HeaderPage(driver).clickCartButton()
                .enterPhoneNumber("12345")
                .clickBookButton();
        Assert.assertFalse(cartPage.isItemBooked(), "Item is booked without address credentials");
    }

    @Test
    public void verifyItemIsBookedWithPhoneAndAddress() {
        PageService pageService = new PageService(driver);
        pageService.openHomePage()
                .addAvailableItemToCart("1");
        CartPage cartPage = new HeaderPage(driver).clickCartButton();
        pageService.bookItem("12345", "221b Baker Street");
        Assert.assertTrue(cartPage.isItemBooked(), "Item is not booked");
    }

    @Test(groups = {"logOut"})
    public void verifyBookedItemsStoredInAccountProfile() {
        HeaderPage headerPage = new HeaderPage(driver);
        PageService pageService = new PageService(driver);
        pageService.openHomePage()
                .login("hello@gmail.com", "hello")
                .addAvailableItemToCart("1");
        headerPage.clickCartButton();
        String bookingNumber = pageService.bookItem("12345", "221b Baker Street")
                .getBookingNumber();
        ProfilePage profilePage = headerPage.clickAccountButton()
                .clickPersonalProfileButton();
        Assert.assertTrue(profilePage.isBookingRegistered(bookingNumber), "Booking is not registered in account profile");
    }
    
    @Test
    public void verifyItemPriceCalculatedCorrectly() {
        CartPage cartPage = new CartPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        PageService pageService = new PageService(driver);
        String item = pageService.openHomePage()
        	.addAvailableItemToCart("2")
        	.getItemName();
        int totalPrice = headerPage.clickCartButton()
        	.getTotalPriceForItem(item);
        Assert.assertTrue(totalPrice == cartPage.getPriceForItem(item) * cartPage.getItemCount(item), "Item price is not calculated correctly");
    }
    
    @Test
    public void verifyTotalPriceCalculatedCorrectly() {
        CartPage cartPage = new CartPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        PageService pageService = new PageService(driver);
        String item = pageService.openHomePage()
        	.addAvailableItemToCart("2")
        	.getItemName();
        int totalPrice = headerPage.clickCartButton()
        	.getTotalPrice();
        Assert.assertTrue(totalPrice == cartPage.getPriceForItem(item) * cartPage.getItemCount(item), "Total price is not calculated correctly");
    }
}
