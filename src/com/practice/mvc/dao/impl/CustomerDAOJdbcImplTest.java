package com.practice.mvc.dao.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDAOJdbcImplTest {

    @Test
    public void get() {
      CustomerDAOJdbcImpl c = new CustomerDAOJdbcImpl();
        System.out.println(c.get(null,null));
    }
}