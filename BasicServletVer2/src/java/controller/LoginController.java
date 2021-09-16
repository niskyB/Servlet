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

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Router.LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!postHandler(request, response)) {
                request.getRequestDispatcher(Router.LOGIN).forward(request, response);
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

        // Get param from form
        String userName = GetParam.getParamString(request, "userName", "User name", 3, 15);
        Integer password = GetParam.getParamInteger(request, "password", "Password", 3, 8);

        // Check param
        if (userName == null || password == null) {
            return false;
        }

        // Check user in database
        User user = userDao.getUserByName(userName);
        if (user == null) {
            request.setAttribute("userNameErrorMessage", "User does not exist.");
            return false;
        }

        // Check password
        if (!Objects.equals(user.getPassword(), password)) {
            request.setAttribute("passwordErrorMessage", "Password is incorrect.");
            return false;
        }

        // Login successful
        request.setAttribute("message", "Welcome " + userName);
        request.setAttribute("userInformation", user.getInformation());

        // Store in session
        session.setAttribute("user", user);
        return true;
    }
}
