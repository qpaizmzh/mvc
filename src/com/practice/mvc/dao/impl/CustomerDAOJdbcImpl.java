package com.practice.mvc.dao.impl;

import com.practice.mvc.dao.CustomerDAO;
import com.practice.mvc.dao.DAO;
import com.practice.mvc.domain.Customer;

import java.util.List;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {

    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        String sql = "select id,name,address,phone from customers where " +
                "name LIKE ? and address LIKE ? and phone LIKE ?";
        System.out.println("JDBC");

        return getForList(sql, cc.getName() == null ? "%%" : "%" + cc.getName() + "%",
                cc.getAddress() == null ? "%%" : "%" + cc.getAddress() + "%",
                cc.getPhone() == null ? "%%" : "%" + cc.getPhone() + "%");
    }

    @Override
    public List<Customer> getAll() {
        String sql = "select* from customers";
        return getForList(sql);
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customers (name,address,phone)value(?,?,?)";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
    }

    @Override
    public Customer get(Integer id) {
        String sql = "select * from customers where id = ?";
        return get(sql, id);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from customers where id = ?";
        update(sql, id);
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customers set name=?,address=?,phone = ? where id = ?";
        update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
    }

    @Override
    public long getCountWithName(String name) {
        String sql = "select count(id) from customers where name = ?";
        long count = getForValue(sql, name);
        return count;
    }
}
