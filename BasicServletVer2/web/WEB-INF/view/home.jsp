<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        ${requestScope.message} <br>
        ${requestScope.userInformation}
        <%
            User user = (User)session.getAttribute("user");
            session.setAttribute("user", user);
        %>
        <a href="UpdatePasswordController">Update password</a>
    </body>
</html>
