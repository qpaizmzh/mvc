package com.practice.mvc.dao.impl;

import com.practice.mvc.dao.CustomerDAO;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAOFactory {
    private static CustomerDAOFactory instance =new CustomerDAOFactory();
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static CustomerDAOFactory getInstance() {
        return instance;
    }

    Map<String, CustomerDAO> map = new HashMap<>();

    private CustomerDAOFactory() {
        map.put("jdbc", new CustomerDAOJdbcImpl());
        map.put("xml", new CustomerDAOXMLImpl());
    }

    public CustomerDAO getCustomerDAO() {
        return map.get(type);
    }
}
