package com.practice.mvc.dao.impl;

import com.practice.mvc.domain.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOJdbcImplTest {
    CustomerDAOJdbcImpl c = new CustomerDAOJdbcImpl();

    @Test
    public void get() {
        System.out.println(c.get(null, null));
    }

    @Test
    public void getAll() {
        List<Customer> list = c.getAll();
        for (Customer cus:
             list) {
            System.out.println(cus);
        }
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setId(23);
        customer.setName("michael jordan");
        customer.setAddress("los angeles");
        customer.setPhone("123");
        c.save(customer);
    }

    @Test
    public void delete() {
        c.delete(24);
    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setId(24);
        customer.setName("kobe bryant");
        customer.setAddress("los angeles");
        customer.setPhone("81");
        c.update(customer);
    }

    @Test
    public void getCountWithName() {
        System.out.println(c.getCountWithName("michael jordan"));
    }
}