package com.practice.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JdbcUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
}