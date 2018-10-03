<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/1
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>add</title>
</head>
<body>
<%
    Object message =request.getAttribute("msg");
    if (message != null) {
%>
<br>
<div style="color: red"><%=message%></div>
<br>
<%
    }
%>
<form action="add.do" method="post">
    id:<input type="text" name="Id" value="<%=request.getParameter("Id")==null?"":
    request.getParameter("Id")%>"><br>
    name:<input type="text" name="name" value="<%=request.getParameter("name")==null?"":
    request.getParameter("name")%>"><br>
    address:<input type="text" name="address" value="<%=request.getParameter("address")==null?"":
    request.getParameter("address")%>"><br>
    phone:<input type="text" name="phone" value="<%=request.getParameter("phone")==null?"":
    request.getParameter("phone")%>"><br>
    <input type="submit" value="添加">
</form>

</body>
</html>
