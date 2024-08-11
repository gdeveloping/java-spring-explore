package tech.gdev.javabasicexplore.javabasic;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExplore {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        testJdbcRecommended();
    }


    /**
     * 推荐方式
     */
    public static void testJdbcRecommended() {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }


    /**
     * Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
     * The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
     */
    public static void testJdbcNotRecommended() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clz = Class.forName("com.mysql.jdbc.Driver");
        Driver d = (Driver) clz.newInstance();
    }


    /**
     * Exception in thread "main" java.lang.InstantiationException: java.sql.Driver
     * Caused by: java.lang.NoSuchMethodException: java.sql.Driver.<init>()
     */
    public static void testJdbcError() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clz = Class.forName("java.sql.Driver");
        Driver d = (Driver) clz.newInstance();
    }


}
