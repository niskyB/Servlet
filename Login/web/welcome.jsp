<%-- 
    Document   : welcome
    Created on : Sep 13, 2021, 9:51:22 PM
    Author     : locnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        ${requestScope.welcomeMessage}
    </body>
</html>
