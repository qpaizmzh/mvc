<%@ page import="com.practice.mvc.domain.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 刘江伟
  Date: 2018/9/25
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>$Title$</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var name = $(this).parent().parent().find("td:eq(1)").text();
                var flag = confirm("需要删除" + name + "的信息吗");

                return flag;
            })
        })
    </script>
</head>
<body>

<form action="get.do" method="post">
    id:<input type="text" name="Id"><br>
    name:<input type="text" name="name"><br>
    address:<input type="text" name="address"><br>
    phone:<input type="text" name="phone"><br>
    <input type="submit" value="全查">
    </form>
<a href="addCustomer.jsp">add new Customer</a>
<br>
<br>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("list");
    if (customers != null && customers.size() > 0) {
%>
<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>address</th>
        <th>phone</th>
        <th>update/delete</th>
    </tr>


    <%
        for (Customer customer :
                customers) {%>
    <tr>
        <td><%=customer.getId()%>
        </td>
        <td><%=customer.getName()%>
        </td>
        <td><%=customer.getAddress()%>
        </td>
        <td><%=customer.getPhone()%>
        </td>
        <td><a href="edit.do?id=<%=customer.getId()%>">update</a>/
            <a href="delete.do?id=<%=customer.getId()%>" class="delete">delete</a>
        </td>
    </tr>

    <%
            }
        }
    %>


</table>
</body>
</html>
