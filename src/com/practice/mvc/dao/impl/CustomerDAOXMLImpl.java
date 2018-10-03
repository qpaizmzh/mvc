package com.practice.mvc.dao.impl;

import com.practice.mvc.dao.CustomerDAO;
import com.practice.mvc.domain.Customer;

import java.util.List;

public class CustomerDAOXMLImpl implements CustomerDAO {
    @Override
    public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
        System.out.println("xml");
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer get(Integer id) {
        System.out.println("getXML");
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public long getCountWithName(String name) {
        return 0;
    }
}
