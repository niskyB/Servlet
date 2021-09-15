<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : admin
    Created on : Sep 15, 2021, 12:11:06 AM
    Author     : locnh
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
      
    <c:forEach var="user" items="${requestScope.users}">
        <div>${user.getFullName()}</div>
        <a href="UpdateUserController?username=${user.getFullName()}"> Update</a>
    </c:forEach>
      
       


    </body>
</html>
