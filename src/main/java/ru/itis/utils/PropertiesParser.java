package ru.itis.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParser{
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("/WEB-INF/classes/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
