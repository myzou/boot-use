package com.example.boot.springdata.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author dyzhan
 * @Description //TODO JDBC工具类
 * @Date 16:02 2019/6/5
 * @return
 **/
public class JdbcUtil {

    /**
     * @return java.sql.Connection
     * @Author dyzhan
     * @Description //TODO 获取Connection
     * @Date 16:04 2019/6/5
     * @Param []
     **/
    public static Connection getConnection() throws Exception {
        InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");
        String driverClass = properties.getProperty("jdbc.driverClass");

        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * @return void
     * @Author dyzhan
     * @Description //TODO 释放DB相关的资源
     * @Date 16:04 2019/6/5
     * @Param [resultSet, statement, connection]
     **/
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
