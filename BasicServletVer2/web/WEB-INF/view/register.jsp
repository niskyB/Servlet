<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="RegisterController" method="POST">
            User: <input type="text" name="userName">
            ${requestScope.userNameErrorMessage} <br><br>
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br><br>
            Confirm password: <input type="password" name="confirmPassword">
            ${requestScope.confirmPasswordErrorMessage} <br><br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
