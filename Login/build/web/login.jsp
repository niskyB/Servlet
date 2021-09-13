<%-- 
    Document   : login
    Created on : Sep 13, 2021, 9:51:11 PM
    Author     : locnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <form action="UserController" method="POST">
            User: <input type="text" name="user">
            ${requestScope.userErrorMessage} <br> <br>
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage}
            ${requestScope.wrongPasswordErrorMessage} <br> <br>
            <input type="submit" value="submit"> ${requestScope.notFoundErrorMessage}
        </form>
    </body>
</html>
