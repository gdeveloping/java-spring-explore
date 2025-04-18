package tech.gdev.springbasicexplore.jdbc.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * 使用 H2 自动配置即可。
 *
 * @author gdev
 * @date 2025/4/17 23:35
 */
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@ConditionalOnProperty(name = "app.db.type", havingValue = "h2")
public class H2AutoConfiguration {
    @Bean
    public InitializingBean initializeDatabase(DataSource dataSource, DatabaseProperties properties) {
        return () -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            for (String schemaLocation : properties.getSqlLocations()) {
                if (schemaLocation.contains("procedure")) {
                    continue;
                }
                populator.addScript(new ClassPathResource(schemaLocation));
            }
            populator.execute(dataSource);
        };
    }
}