package org.WebstaurantStore.Pages;

import org.WebstaurantStore.Utilities.ConfigurationReader;
import org.WebstaurantStore.Utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

public class DashboardPage {

    public WebDriver driver;


    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        initialSetup();
    }


    @FindBy(xpath = "//input[@id='searchval']")
    private WebElement searchBoxXpath;

    /**
     * Search products
     * @param searchText text to validate against
     */
    public void searchProduct(String searchText) {
        searchBoxXpath.sendKeys(searchText + Keys.ENTER);
    }

    /**
     * Setup browser and navigate to default URL
     */
    public void initialSetup(){
        long impWait = Long.parseLong(ConfigurationReader.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("mainURL"));

    }

    /**
     * Teardown driver instance and pool
     */
    public void tearDown(){
        Driver.closeDriver();
    }


}
