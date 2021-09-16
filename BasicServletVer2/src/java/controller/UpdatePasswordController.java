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
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Router.UPDATEPASSWORD).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!postHandler(request, response)) {
                request.getRequestDispatcher(Router.UPDATEPASSWORD).forward(request, response);
                return;
            }
            request.getRequestDispatcher(Router.HOME).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();

        // Get param
        User user = (User) session.getAttribute("user");
        Integer password = GetParam.getParamInteger(request, "password", "Password", 3, 8);
        Integer newPassword = GetParam.getParamInteger(request, "newPassword", "New password", 3, 8);
        Integer confirmPassword = GetParam.getParamInteger(request, "confirmPassword", "Confirm password", 3, 8);

        // Check param
        if (password == null || newPassword == null || confirmPassword == null) {
            return false;
        }

        // Check password
        if (!Objects.equals(password, user.getPassword())) {
            request.setAttribute("passwordErrorMessage", "Password is incorrect.");
            return false;
        }

        // Check new password and confirm password
        if (!Objects.equals(newPassword, confirmPassword)) {
            request.setAttribute("confirmPasswordErrorMessage", "Confirm password does not match with new password.");
            return false;
        }

        // Update password
        try {
            userDao.updatePassword(user.getUserName(), newPassword);
        } finally {
            // Set new password for user
            user.setPassword(newPassword);

            // Send message
            request.setAttribute("message", "Welcome " + user.getUserName());
            request.setAttribute("userInformation", user.getInformation());

            // Store in session
            session.setAttribute("user", user);
            return true;
        }
    }
}
