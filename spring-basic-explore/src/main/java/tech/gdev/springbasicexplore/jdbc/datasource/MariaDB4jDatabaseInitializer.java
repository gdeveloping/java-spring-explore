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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author gdev
 * @date 2025/4/17 23:07
 */
@Component
@EnableConfigurationProperties(DatabaseProperties.class)
@ConditionalOnProperty(name = "app.db.type", havingValue = "mariadb4j")
@RequiredArgsConstructor
public class MariaDB4jDatabaseInitializer implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DatabaseProperties properties;

    private static final String SQL_SCRIPT_ENCODING = "UTF-8";
    private static final String DELIMITER = ";;"; // 自定义分隔符

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        for (String location : properties.getSqlLocations()) {
            System.out.println(location.trim());
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setSqlScriptEncoding("UTF-8");
            populator.addScript(new ClassPathResource(location.trim()));
            DatabasePopulatorUtils.execute(populator, dataSource);
        }
        String sqlProcedure =
                "CREATE PROCEDURE get_user_and_orders(IN user_id_param INT) \n" +
                        "BEGIN  \n" +
                        "    SELECT * FROM user WHERE id = user_id_param;\n" +
                        "    SELECT * FROM `order` WHERE user_id = user_id_param;\n" +
                        "END;\n";

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlProcedure);
        }
        System.out.println("存储过程创建成功！");
    }

    private void initializeDatabase2() throws SQLException, IOException {
        for (String location : properties.getSqlLocations()) {
            if (location.contains("procedure")) {
                executeProcedureScript(location);
            } else {
                executeCommonScript(location);
            }
        }
    }

    private void executeProcedureScript(String location) throws SQLException, IOException {
        String procedureScript = new ClassPathResource(location.trim()).getInputStream().toString();
        System.out.println(procedureScript);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            // 临时更改分隔符以便正确处理存储过程中的分号
            stmt.execute("DELIMITER " + DELIMITER);
            // 执行存储过程创建语句
            stmt.execute(procedureScript);
            // 恢复默认分隔符
            stmt.execute("DELIMITER ;");
        }
    }

    private void executeCommonScript(String scriptLocation) throws SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding(SQL_SCRIPT_ENCODING);
        populator.addScript(new ClassPathResource(scriptLocation));
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}