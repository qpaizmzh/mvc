package com.practice.mvc.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    /**
     * 释放 Connection 连接
     *
     * @param connection
     */
    public static void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataSource dataSource;

    //只允许有一个连接池存在，所以直接使用静态代码块来执行一次就可以了
    static {
        dataSource = new ComboPooledDataSource("mvc");
    }


    /**
     * 返回数据源的一个 Connection 对象
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }
}
