<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        ${requestScope.message}
        ${requestScope.user}
        <% 
            User user =(User) session.getAttribute("user");
            session.setAttribute("user", user);
        %>
        <br>
        <a href="updatePassword.jsp">Update password</a>
    </body>
</html>
