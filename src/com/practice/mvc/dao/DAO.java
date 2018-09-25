package com.practice.mvc.dao;

import org.apache.commons.dbutils.QueryRunner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

public class DAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public DAO() {
        Type type = this.getClass().getGenericSuperclass();

        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            Type [] types = parameterizedType.getActualTypeArguments();

            if (types[0] instanceof Class){
               clazz =  (Class<T>)types[0];
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
    public <E> E getForValue(String sql, Object... args) {
        return null;
    }

    /**
     * 返回 T 所对应的 List
     *
     * @param sql
     * @param args
     * @return
     */
    public List<T> getForList(String sql, Object... args) {


        return null;
    }

    /**
     * 返回对应的 T 的一个实例类的对象.
     *
     * @param sql
     * @param args
     * @return
     */
    public Class<T> get(String sql, Object... args) {


        return clazz;
    }

    /**
     * 该方法封装了 INSERT、DELETE、UPDATE 操作.
     *
     * @param sql:  SQL 语句
     * @param args: 填充 SQL 语句的占位符.
     */
    public void update(String sql, Object... args) {

    }
}
