<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/2
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (request.getAttribute("msg") != null) {
%>
<br>
<span style="color: red"><%=request.getAttribute("msg")%></span>
<br>
<%}%>
<form action=" update.do" method="post">
    <input type="hidden" name="id" value="<%=request.getAttribute("id")==null?"":
    request.getAttribute("id")%>">

    <input type="hidden" name="oldname" value="<%=request.getAttribute("oldname")==null?"":
    request.getAttribute("oldname")%>">

    name:<input type="text" name="name" value="<%=request.getAttribute("name")==null?"":
    request.getAttribute("name")%>"><br>
    address:<input type="text" name="address" value="<%=request.getAttribute("address")==null?"":
    request.getAttribute("address")%>"><br>
    phone:<input type="text" name="phone" value="<%=request.getAttribute("phone")==null?"":
    request.getAttribute("phone")%>"><br>
    <input type="submit" value="更新">
</form>
</body>
</html>
