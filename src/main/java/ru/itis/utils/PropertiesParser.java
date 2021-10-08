package ru.itis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser{
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("application.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
