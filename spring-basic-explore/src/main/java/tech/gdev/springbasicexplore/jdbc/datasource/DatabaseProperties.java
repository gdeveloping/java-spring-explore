package tech.gdev.springbasicexplore.jdbc.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gdev
 * @date 2025/4/17 23:33
 */
@ConfigurationProperties(prefix = "app.db")
@Getter
@Setter
public class DatabaseProperties {
    private String type;
    private String name;
    private String[] sqlLocations;

    private Mariadb4j mariadb4j;

    @Getter
    @Setter
    public static class Mariadb4j {
        private int port;
        private String dataDir;
        private String args;
    }
}