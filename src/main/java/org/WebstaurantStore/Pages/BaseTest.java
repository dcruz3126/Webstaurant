package org.WebstaurantStore.Pages;

import org.WebstaurantStore.Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected DashboardPage dashboard;
    protected CartPage cartPage;
    protected SearchPage searchPage;

    public BaseTest() {
        PageFactory.initElements(driver, this);
    }

    @BeforeTest
    public void setup() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.webstaurantstore.com/");

        initializePages();
    }

    /**
     * Create POM page object to pass onto test pages for usability.
     */
    private void initializePages() {
        dashboard = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        searchPage = new SearchPage(driver);
//
    }

    /**
     * Close driver instance
     */
    @AfterTest
    public void tearDown() {
        Driver.closeDriver();
    }

}
