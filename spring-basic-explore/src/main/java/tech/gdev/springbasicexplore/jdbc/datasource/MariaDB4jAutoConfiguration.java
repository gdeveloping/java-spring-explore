package tech.gdev.springbasicexplore.jdbc.datasource;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author gdev
 * @date 2025/4/17 23:18
 */
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@ConditionalOnProperty(name = "app.db.type", havingValue = "mariadb4j")
public class MariaDB4jAutoConfiguration {

    @Bean
    public DB mariaDB4j(DatabaseProperties properties) throws ManagedProcessException {
        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
        config.setPort(properties.getMariadb4j().getPort());
        config._getArgs().addAll(Arrays.asList(properties.getMariadb4j().getArgs().split(" ")));
        DB db = DB.newEmbeddedDB(config.build());
        try {
            db.start();
            db.createDB(properties.getName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to start MariaDB4j", e);
        }
        return db;
    }

    @Bean
    @DependsOn("mariaDB4j")  // 确保在 mariaDB4j bean 之后创建
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
}