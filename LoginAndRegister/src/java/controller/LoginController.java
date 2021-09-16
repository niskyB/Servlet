package controller;

import dao.UserDao;
import helper.GetParam;
import java.io.IOException;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        User user = new User();
        String username;
        Integer password;

        // Get input from user
        username = GetParam.getParamString(request, "user", "User", 3, 15);
        password = GetParam.getParamInteger(request, "password", "Password", 3, 9);

        // Check input
        if (username == null || password == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Connect database
        try {
            user = userDao.getUserByUsername(username);
            if (user == null) {
                request.setAttribute("errorMessage", "The user does not exist.");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            } else if (!Objects.equals(user.getPassword(), password)) {
                request.setAttribute("passwordErrorMessage", "The password is incorrect.");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            request.setAttribute("message", "Welcome " + username);
            request.setAttribute("user", user.getInformation());
            session.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
