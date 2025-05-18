package tech.gdev.springbasicexplore.jdbc.mysql;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author gdev
 * @date 2025/5/18 17:01
 */
public class MySQLCharsetExplore {
    private static final String JDBC_DRIVER;
    private static final String DB_URL;
    private static final String USER;
    private static final String PASS;
    private static final String DB_URL_UTF8;
    private static final String DB_URL_GBK;

    static {
        try {
            ConfigurableEnvironment environment = new StandardEnvironment();
            ResourcePropertySource propertySource = new ResourcePropertySource(
                    new ClassPathResource("application.properties"));
            environment.getPropertySources().addFirst(propertySource);
            JDBC_DRIVER = environment.getProperty("spring.datasource.driver-class-name");
            DB_URL = environment.getProperty("spring.datasource.url");
            USER = environment.getProperty("spring.datasource.username");
            PASS = environment.getProperty("spring.datasource.password");
            DB_URL_UTF8 = DB_URL + "?characterEncoding=utf8&useUnicode=false";
            DB_URL_GBK = DB_URL + "?characterEncoding=gbk&useUnicode=false";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 汉字”中“编码
     * UTF8: E4B8AD
     * GBK: D6D0
     *
     * characterEncoding：指定客户端与服务器之间通信时使用的字符编码。
     * useUnicode=false：表示不使用 Unicode（即 UTF-8 或 UTF-16）编码进行通信，而是使用指定的 characterEncoding
     * _charset 语法
     *
     * input dbUrl: jdbc:mysql://localhost:3306/local_test_db?characterEncoding=utf8&useUnicode=false
     * input sql: insert into test_code(id, code, note) values (101, 1, _utf8'中 from jdbc ')
     * output: (101, 1, 中 from jdbc )
     *
     * input dbUrl: jdbc:mysql://localhost:3306/local_test_db?characterEncoding=utf8&useUnicode=false
     * input sql: insert into test_code(id, code, note) values (101, 1, _gbk'中 from jdbc ')
     * java.sql.SQLException: Incorrect string value: '\xAD from...' for column 'note' at row 1
     *
     * input dbUrl: jdbc:mysql://localhost:3306/local_test_db?characterEncoding=gbk&useUnicode=false
     * input sql: insert into test_code(id, code, note) values (101, 1, _utf8'中 from jdbc ')
     * java.sql.SQLException: Incorrect string value: '\xD6\xD0 fro...' for column 'note' at row 1
     *
     * input dbUrl: jdbc:mysql://localhost:3306/local_test_db?characterEncoding=gbk&useUnicode=false
     * input sql: insert into test_code(id, code, note) values (101, 1, _gbk'中 from jdbc ')
     * output: (101, 1, 中 from jdbc )
     */
    public static void main(String[] args) {
        String noteValueUtf8 = "_utf8'中 from jdbc '";
        String noteValueGbk = "_gbk'中 from jdbc '";

//        testCase(DB_URL_UTF8, noteValueUtf8); // OK
//        testCase(DB_URL_UTF8, noteValueGbk); // ERROR
//        testCase(DB_URL_GBK, noteValueUtf8); // ERROR
        testCase(DB_URL_GBK, noteValueGbk); // OK
    }
    public static void testCase(String dbUrl, String noteValue) {
        System.out.println("\n***");
        System.out.println("input dbUrl: " + dbUrl);

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, USER, PASS); Statement stmt = conn.createStatement();) {
            stmt.execute("TRUNCATE TABLE test_code;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, USER, PASS); Statement stmt = conn.createStatement();) {
            stmt.execute("SET NAMES UTF8MB4;");
            String sql = "insert into test_code(id, code, note) values (101, 1, " + noteValue + ")";
            System.out.println("input sql: " + sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbUrl, USER, PASS); Statement stmt = conn.createStatement();) {
            stmt.execute("SET NAMES UTF8MB4;");
            String sql = "select * from test_code;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String note = resultSet.getString("note");
                System.out.println("output: (" + id + ", " + code + ", " + note + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
