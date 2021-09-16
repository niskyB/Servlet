
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update password Page</title>
    </head>
    <body>
        <%
            User user =(User) session.getAttribute("user");
            String userName = user.getUserName();
        %>
        <h1>Hello <%=userName%></h1>
        <form action="UpdatePasswordController" method="POST">
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br><br>
            New password: <input type="password" name="newPassword">
            ${requestScope.newPasswordErrorMessage} <br><br>
            Confirm password: <input type="password" name="confirmPassword">
            ${requestScope.confirmPasswordErrorMessage} <br><br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
