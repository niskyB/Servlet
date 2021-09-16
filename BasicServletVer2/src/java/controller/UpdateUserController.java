package controller;

import dao.UserDao;
import helper.GetParam;
import helper.Router;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!postHandler(request, response)) {
                request.setAttribute("userName", request.getParameter("userName"));
                request.getRequestDispatcher(Router.UPDATEUSER).forward(request, response);
                return;
            }
            response.sendRedirect("UserListController");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();

        // Get param
        String userName = GetParam.getParamString(request, "userName", "User name", 3, 15);
        User user = userDao.getUserByName(userName);
        Integer password = GetParam.getParamInteger(request, "password", "Password", 3, 8);
        Integer confirmPassword = GetParam.getParamInteger(request, "confirmPassword", "Confirm password", 3, 8);
        Integer role = GetParam.getParamInteger(request, "role", "Role", 0, 8);

        // Check param
        if (user == null || password == null || confirmPassword == null || role == null) {
            return false;
        }

        // Check password
        if (!Objects.equals(password, confirmPassword)) {
            request.setAttribute("confirmPasswordErrorMessage", "Confrim password does not match with password.");
            return false;
        }

        try {
            userDao.updateUser(userName, password, role);
        } finally {
            return true;
        }
    }

}
