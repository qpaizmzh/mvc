package com.practice.mvc.Servlet;

import com.practice.mvc.dao.CustomerDAO;
import com.practice.mvc.dao.impl.CriteriaCustomer;
import com.practice.mvc.dao.impl.CustomerDAOFactory;
import com.practice.mvc.dao.impl.CustomerDAOJdbcImpl;
import com.practice.mvc.domain.Customer;
import javafx.beans.binding.ObjectExpression;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "*.do")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO =CustomerDAOFactory.getInstance().getCustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.lastIndexOf("."));

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
        //ServletContext servletContext = request.getServletContext();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (customerDAO.getCountWithName(name) > 0) {
            request.setAttribute("msg", "名字" + name + "已经被占用，请确认");

            request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
            return;
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        customerDAO.save(customer);

        response.sendRedirect("index.jsp");
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

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPath = "error.jsp";

        try {
            Customer customer = customerDAO.get(Integer.parseInt(request.getParameter("id")));
           if (customer!=null){
               forwardPath ="update.jsp";
               request.setAttribute("id", customer.getId());
               request.setAttribute("oldname", customer.getName());
               request.setAttribute("name", customer.getName());
               request.setAttribute("address", customer.getAddress());
               request.setAttribute("phone", customer.getPhone());
           }

        }catch (Exception e){}
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String oldname = request.getParameter("oldname");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("oldname", oldname);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);

        if (!request.getAttribute("oldname").equals(name)
                && customerDAO.getCountWithName(name) > 0) {
            String msg = "此用户名" + name + "已经被使用了，请检查";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        customerDAO.getCountWithName(request.getParameter("name"));


        Customer customer = customerDAO.get(Integer.parseInt(request.getParameter("id")));
        customer.setName(request.getParameter("name"));
        customer.setAddress(request.getParameter("address"));
        customer.setPhone(request.getParameter("phone"));
        customerDAO.update(customer);
        response.sendRedirect("get.do");
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
