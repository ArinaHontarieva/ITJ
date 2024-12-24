package main.java.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlatformPropertiesUtil {
    private static final String PROPERTIES_FILE = "src/main/resources/config.properties";
    private static Properties properties;

    public PlatformPropertiesUtil() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(PROPERTIES_FILE);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDatabaseType() {
        return properties.getProperty("dbType");
    }

    public String getDatabaseUsername() {
        return properties.getProperty("dbUsername");
    }

    public String getDatabasePassword() {
        return properties.getProperty("dbPassword");
    }

    public String getDatabaseUrl() {
        return properties.getProperty("dbUrl");
    }
}
