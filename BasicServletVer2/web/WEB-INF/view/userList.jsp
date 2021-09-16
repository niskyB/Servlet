<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list Page</title>
    </head>
    <body>
        <h1>Hello Admin!</h1>
        ${requestScope.errorMessage}
        <c:forEach var="user" items="${requestScope.users}">
            <div>${user.getUserName()}</div>
            <a href="UpdateUserViewController?username=${user.getUserName()}"> Update</a>
        </c:forEach>
    </body>
</html>
