package by.molchanov.humanresources.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PageLocationManager {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final PageLocationManager PAGE_LOCATION_MANAGER = new PageLocationManager();
    private static final String PAGE_LOCATION_FILE_NAME = "page_location.properties";
    private Properties properties = new Properties();

    private PageLocationManager() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PAGE_LOCATION_FILE_NAME)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
    }

    public static PageLocationManager getInstance() {
        return PAGE_LOCATION_MANAGER;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
