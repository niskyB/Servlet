<%@page import="model.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : updateuser
    Created on : Sep 15, 2021, 7:44:47 PM
    Author     : locnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <h1>Update information</h1>
        <form action="UpdateUserController?user=${requestScope.user.getFullName()}&doPut=true" method="POST">
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br> <br>
            Confirm password: <input type="password" name="confirmPassword">
            ${requestScope.confirmPasswordErrorMessage} <br> <br>
            Role: <input type="text" name="role" placeholder="${user.getRole()}">
            ${requestScope.roleErrorMessage}
            <input type="submit" value="submit">
        </form>



    </body>
</html>
