package tech.gdev.springbasicexplore.jdbc.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author gdev
 * @date 2025/6/29 11:19
 */
@Component
@EnableConfigurationProperties(DatabaseProperties.class)
@ConditionalOnProperty(name = "app.db.type", havingValue = "external")
@RequiredArgsConstructor
public class ExternalDatabaseInitializer implements InitializingBean {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private DatabaseProperties properties;

    private static final String SQL_SCRIPT_ENCODING = "UTF-8";

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException, IOException {
        for (String location : properties.getSqlLocations()) {
            if (location.contains("data_init_dml")) {
                executeCommonScript(location);
            }
        }
    }

    private void executeCommonScript(String scriptLocation) throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding(SQL_SCRIPT_ENCODING);
        populator.addScript(new ClassPathResource(scriptLocation));
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
