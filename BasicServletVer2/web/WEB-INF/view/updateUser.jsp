<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user Page</title>
    </head>
    <body>
        <form action="UpdateUserController?userName=${requestScope.user.getUserName()}" method="POST">
            User: ${requestScope.user.getUserName()} <br><br>
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage} <br><br>
            Confirm password: <input type="password" name="confirmPassword">
            ${requestScope.confirmPasswordErrorMessage} <br><br>
            Role: <input type="text" name="role">
            ${requestScope.roleErrorMessage} <br><br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
