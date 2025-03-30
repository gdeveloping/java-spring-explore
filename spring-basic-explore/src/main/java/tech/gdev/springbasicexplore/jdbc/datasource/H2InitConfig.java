package tech.gdev.springbasicexplore.jdbc.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @author gdev
 * @date 2025/3/30 16:07
 */
@Configuration
public class H2InitConfig {
    @Bean
    public InitializingBean initializeDatabase(DataSource dataSource) {
        return () -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/h2/v1/schema.sql"));
            populator.addScript(new ClassPathResource("db/h2/v1/data.sql"));
            populator.execute(dataSource);
        };
    }
}
