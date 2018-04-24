package by.molchanov.humanresources.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolConfiguration {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DB_CONFIGURATION_FILE_NAME = "db_configuration.properties";
    private String user;
    private String password;
    private int poolSize;
    private String url;

    public ConnectionPoolConfiguration() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_CONFIGURATION_FILE_NAME)) {
            properties.load(inputStream);
            user = properties.getProperty("db.configuration.user");
            password = properties.getProperty("db.configuration.password");
            poolSize = Integer.parseInt(properties.getProperty("db.configuration.pool.size"));
            url = properties.getProperty("db.configuration.url");
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getUrl() {
        return url;
    }
}
