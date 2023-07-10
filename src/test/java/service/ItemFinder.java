package service;

import page.ShopPage;

public class ItemFinder {

    public ShopPage shopPage =
            new ShopPage();

    public ItemFinder getItem(String homePageUrl, String searchRequest) {
        shopPage.openPage(homePageUrl);
        return this;
    }

    public void doSmth(){}
}
