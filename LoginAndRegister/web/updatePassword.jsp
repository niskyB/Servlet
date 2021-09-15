<%-- 
    Document   : updatepassword
    Created on : Sep 14, 2021, 7:07:09 PM
    Author     : locnh
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update password</title>
    </head>
    <body>
        <%
            System.out.println("hello");
            User user = (User) session.getAttribute("user");
            String username = user.getFullName();
            session.setAttribute("user", user);
        %>
        <h1>Hello <%=username%></h1>
        <form action="UpdatePasswordController" method="POST">
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br> <br>
            New password: <input type="password" name="newPassword">
            ${requestScope.newPasswordErrorMessage} <br> <br>
            Confirm password: <input type="password" name="confirmPassword">
            ${requestScope.confirmPasswordErrorMessage} <br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
