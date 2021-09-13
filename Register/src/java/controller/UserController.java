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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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
        String username;
        Integer password, confirmPassword;
        username = GetParam.getParamString(request, "user", "User", 3, 20);
        password = GetParam.getParamInteger(request, "password", "Password", 6, 20);
        confirmPassword = GetParam.getParamInteger(request, "confirmPassword", "Confirm password", 6, 20);
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                request.setAttribute("existErrorMessage", "User name has aldready exist.");
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!Objects.equals(password, confirmPassword)) {
            request.setAttribute("confirmPasswordErrorMessage", "Password and confirm password is different.");
        }
        if (username == null || password == null || !Objects.equals(password, confirmPassword)) {
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
//            userID = userDao.getLastId();
            userDao.addUser(username, password);
        } catch (Exception ex) {

        }
        request.setAttribute("welcomeMessage", "Welcome " + username);
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
