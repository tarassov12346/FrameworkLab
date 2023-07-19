package test;

import page.ShopPage;
import page.CartPage;
import page.HeaderPage;
import org.testng.annotations.Test;
import org.testng.Assert;

public class CartTest extends CommonConditions {
    @Test
    public void verifyItemAddedtoCartAppearInCartPage() {
        String itemAddedToCart = new ShopPage(driver).openPage()
        	.clickItem()
        	.enterItemCount("1")
        	.clickAddToCartButton()
        	.getItemName();        
        CartPage cartPage = new HeaderPage(driver).clickCartButton();
        Assert.assertTrue(cartPage.isItemInCart(itemAddedToCart), "Ordered item is not added to cart and not visible in cartPage");
    }
    
    @Test
    public void verifyDeleteButtonRemovesItemFromCart() {
        String itemAddedToCart = new ShopPage(driver).openPage()
        	.clickItem()
        	.enterItemCount("1")
        	.clickAddToCartButton()
        	.getItemName();
        CartPage cartPage = new HeaderPage(driver).clickCartButton()
        	.clickRemoveItemButton(itemAddedToCart);
        Assert.assertTrue(cartPage.isItemRemovedFromCart(itemAddedToCart), "Item is not removed from cart");
    }    
}
