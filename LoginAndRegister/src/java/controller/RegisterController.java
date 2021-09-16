package controller;

import dao.UserDao;
import helper.GetParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author locnh
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        String username;
        Integer password, confirmPassword;
        username = GetParam.getParamString(request, "user", "User", 3, 20);
        password = GetParam.getParamInteger(request, "password", "Password", 6, 20);
        confirmPassword = GetParam.getParamInteger(request, "confirmPassword", "Confirm password", 6, 20);
        if (username == null || password == null || confirmPassword == null || !Objects.equals(password, confirmPassword)) {
            if (confirmPassword != null && !Objects.equals(password, confirmPassword)) {
                request.setAttribute("confirmErrorMessage", "Password and confirm password is different.");
            }
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                request.setAttribute("errorMessage", "User name has aldready exist.");
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            userDao.addUser(username, password);
        } catch (Exception ex) {

        }
        try {
            User user = userDao.getUserByUsername(username);
            session.setAttribute("user", user);
            request.setAttribute("user", user.getInformation());
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", "Welcome " + username);
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

}
