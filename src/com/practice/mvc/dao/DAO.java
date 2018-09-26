package com.practice.mvc.dao;

import com.practice.mvc.db.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public DAO() {
        Type type = this.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();

            if (types[0] instanceof Class) {
                clazz = (Class<T>) types[0];
            }

        }


    }

    /**
     * 返回某一个字段的值：例如返回某一条记录的 customerName, 或返回数据表中有多少条记录等.
     *
     * @param sql
     * @param args
     * @return
     */
    protected  <E> E getForValue(String sql, Object... args) {
        Connection connection = null;
        E result = null;
        try {
            connection = JdbcUtils.getConnection();
            result = (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
        return result;
    }

    /**
     * 返回 T 所对应的 List
     *
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object... args) {
        Connection connection = null;
        List<T> list = new ArrayList<>();
        try {
            connection = JdbcUtils.getConnection();
            list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
        return list;
    }

    /**
     * 返回对应的 T 的一个实例类的对象.
     *
     * @param sql
     * @param args
     * @return
     */
    public T get(String sql, Object... args) {
        Connection connection = null;
        T result = null;
        try {
            connection = JdbcUtils.getConnection();
            result = queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }

        return result;
    }

    /**
     * 该方法封装了 INSERT、DELETE、UPDATE 操作.
     *
     * @param sql:  SQL 语句
     * @param args: 填充 SQL 语句的占位符.
     */
    public void update(String sql, Object... args) {
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
    }
}
