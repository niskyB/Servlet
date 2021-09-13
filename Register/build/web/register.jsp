<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register page</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <form action="UserController" method="POST">
            User: <input type="text" name="user"> 
            ${requestScope.userErrorMessage} 
            ${requestScope.existErrorMessage} <br> <br>
            Password: <input type="password" name="password">
            ${requestScope.passwordErrorMessage}<br> <br>
            Confirm password: <input type="password" name="confirmPassword"> 
            ${requestScope.confirmPasswordErrorMessage}<br> <br>
            <input type="submit" value="submit"> ${requestScope.addErrorMessage}
        </form>
    </body>
</html>
