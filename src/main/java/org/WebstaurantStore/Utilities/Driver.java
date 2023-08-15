package org.WebstaurantStore.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver {

    private Driver(){

    }

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
/**
 * Creates driver instance in a ThreadLocal variable. Can include switch method for different browser types
 * @return driver
 */
    public static WebDriver getDriver(){
        if (driverPool.get() == null){
            synchronized (Driver.class){
                String browser = ConfigurationReader.getProperty("browser");
                switch (browser) {
                    case "chrome" -> {
                        WebDriverManager.chromedriver().setup();
                        driverPool.set(new ChromeDriver());
                    }
                    case "edge" -> {
                        WebDriverManager.edgedriver().setup();
                        driverPool.set(new EdgeDriver());
                    }
                }
                driverPool.get().manage().window().maximize();
            }

            return driverPool.get();
        }

        return null;
    }

    /**
     * Closes out driver instance and removes driver pool
     */
    public static void closeDriver(){
        if(driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
