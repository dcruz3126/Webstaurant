package org.WebstaurantStore.Tests;

import org.WebstaurantStore.Pages.BaseTest;
import org.testng.annotations.Test;

public class SearchAndCartTest extends BaseTest {


    @Test
    public void BasicSearchAndCartTest(){


        dashboard.initialSetup();
        dashboard.searchProduct("stainless work table");
        searchPage.validateAllSearchResults("table");
        // REVIEWER: validateAllSearchResults() returns a boolean value which should be asserted/verified. Because not all the items descriptions found contain the word title, this test would fail.
        cartPage.addLastItemToCart();
        cartPage.verifyItemsInCart(1);
        cartPage.emptyCartContents();
        cartPage.verifyItemsInCart(0);

    }

}
