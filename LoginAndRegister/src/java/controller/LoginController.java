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
import model.User;

/**
 *
 * @author locnh
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = new User();
        String username;
        Integer password;
        username = GetParam.getParamString(request, "user", "User", 3, 15);
        password = GetParam.getParamInteger(request, "password", "Password", 3, 9);

        if (username == null || password == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

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
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
