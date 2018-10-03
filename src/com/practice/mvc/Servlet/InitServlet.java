package com.practice.mvc.Servlet;

import com.practice.mvc.dao.impl.CustomerDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        CustomerDAOFactory.getInstance().setType("jdbc");

        //读取类路径下的 switch.properties 文件
        InputStream in =
                getServletContext().getResourceAsStream("/WEB-INF/classes/customer.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            //获取 switch.properties 的 type 属性值
            String type = properties.getProperty("type");
            //赋给了 CustomerDAOFactory 的 type 属性值
            CustomerDAOFactory.getInstance().setType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
