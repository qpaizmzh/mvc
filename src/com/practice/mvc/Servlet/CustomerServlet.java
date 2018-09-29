package com.practice.mvc.Servlet;

import com.practice.mvc.dao.CustomerDAO;
import com.practice.mvc.dao.impl.CriteriaCustomer;
import com.practice.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.practice.mvc.domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "*.do")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.indexOf("."));

        try {
            Method method = this.getClass().getDeclaredMethod(servletPath, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            int num = Integer.parseInt(id);
            customerDAO.delete(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("get.do");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("update");
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CriteriaCustomer cc = new CriteriaCustomer();

        cc.setName(request.getParameter("name"));
        cc.setAddress(request.getParameter("address"));
        cc.setPhone(request.getParameter("phone"));

        List<Customer> list = customerDAO.getForListWithCriteriaCustomer(cc);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
