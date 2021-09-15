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
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao = new UserDao();
        String username = user.getFullName();
        Integer password = user.getPassword();

        // Check password
        Integer inputedPassword = GetParam.getParamInteger(request, "password", "Password", 3, 9);
        Integer newPassword = GetParam.getParamInteger(request, "newPassword", "New password", 3, 9);
        Integer confirmPassword = GetParam.getParamInteger(request, "confirmPassword", "Confirm password", 3, 9);
        if (inputedPassword == null || newPassword == null || confirmPassword == null) {
            getServletContext().getRequestDispatcher("/updatePassword.jsp").forward(request, response);
            return;
        }
        if (!Objects.equals(newPassword, confirmPassword)) {
            request.setAttribute("confirmPasswordErrorMessage", "Password and confirm password are different.");
            getServletContext().getRequestDispatcher("/updatePassword.jsp").forward(request, response);
            return;
        }
        if (!Objects.equals(inputedPassword, password)) {
            request.setAttribute("passwordErrorMessage", "Wrong password");
            getServletContext().getRequestDispatcher("/updatePassword.jsp").forward(request, response);
            return;
        }
        try {
            // Update password
            userDao.updatePassword(username, newPassword);
        } catch (Exception ex) {
            Logger.getLogger(UpdatePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setPassword(newPassword);
        session.setAttribute("user", user);
        request.setAttribute("message", "Welcome " + username);
        request.setAttribute("user", user.getInformation());
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
