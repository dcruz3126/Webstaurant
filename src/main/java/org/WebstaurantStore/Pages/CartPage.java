package org.WebstaurantStore.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {


    WebDriver driver;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    @FindBy(xpath = "//button[@class='border-solid border box-border cursor-pointer inline-block text-center no-underline antialiased focus-visible:outline focus-visible:outline-offset-2 focus-visible:outline-2 mr-2 rounded-normal text-base leading-normal px-7 py-2-1/2 hover:bg-green-800 text-white text-shadow-black-60 bg-green-700 border-black-10 focus-visible:outline-green-800 btn align-middle font-semibold']")
    private WebElement emptyCartConfirmationButtonXpath;

    @FindBy(xpath = "//button[contains(text(),'Empty Cart')]")
    private WebElement emptyCartButtonXpath;

    @FindBy(xpath = "//span[@id='cartItemCountSpan']")
    private WebElement cartCountButtonXpath;

    @FindBy(xpath = "(//input[@type='submit'])[62]")
    WebElement lastItemOnPage;

    /**
     * Add last item on current page to the cart
     */
    public void addLastItemToCart() {
        lastItemOnPage.submit();
    }

    /**
     * Verifies the number of items in the cart matches expected
     * @param numberOfCartItems number of items expected to be in the cart
     */

    public void verifyItemsInCart(int numberOfCartItems) {
        driver.navigate().refresh();
        Assert.assertEquals(cartCountButtonXpath.getText(), String.valueOf(numberOfCartItems));
    }

    /**
     * Empty cart contents
     */
    public void emptyCartContents() {
        cartCountButtonXpath.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
        emptyCartButtonXpath.click();
        emptyCartConfirmationButtonXpath.click();
        verifyItemsInCart(0);
    }


}
