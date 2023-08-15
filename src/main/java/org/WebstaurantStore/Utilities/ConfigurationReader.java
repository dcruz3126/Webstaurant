package org.WebstaurantStore.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    /**
     * Read details from config.properties file to establish universal project properties
     */
    static{
        try {
            String path = "config.properties";
            FileInputStream inputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * retrieve specific property value
     * @param keyName name of property
     * @return property value
     */
    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }
}
