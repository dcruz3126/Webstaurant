package org.WebstaurantStore.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests {

    @Test
    public void testConfigReader(){
        System.out.println("explicitWait = " + ConfigurationReader.getProperty("explicitWait"));
        System.out.println("implicitWait= " + ConfigurationReader.getProperty("implicitWait"));
        System.out.println("mainUrl = " + ConfigurationReader.getProperty("mainURL"));
        System.out.println("browser = " + ConfigurationReader.getProperty("browser"));

    }

    @Test
    public void testDriver (){
        WebDriver driver = Driver.getDriver();
        Assert.assertNotNull(driver);
        Driver.closeDriver();
    }
}
