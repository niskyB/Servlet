<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <form action="LoginController" method="POST">
            User: <input type="text" name="userName">
            ${requestScope.userNameErrorMessage} <br><br>
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br><br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
