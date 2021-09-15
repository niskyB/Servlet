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
@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = GetParam.getParamString(request, "username", "User name", 3, 15);
        UserDao userDao = new UserDao();

        try {
            User user = userDao.getUserByUsername(name);
            if (user == null) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
                return;
            }
            request.setAttribute("user", user);
            doPost(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("doPut") != null && request.getParameter("doPut").equals("true")) {
            request.setAttribute("user", request.getParameter("user"));
            doPut(request, response);
            return;
        }
        User user = (User) request.getAttribute("user");
        if (user == null) {
            System.out.println("Check");

            request.getRequestDispatcher("admin.jsp").forward(request, response);
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("updateuser.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = (User) userDao.getUserByUsername((String) req.getAttribute("user"));
        } catch (Exception ex) {
            Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Integer password = GetParam.getParamInteger(req, "password", "Password", 3, 9);
        Integer confirmPassword = GetParam.getParamInteger(req, "confirmPassword", "Confirm password", 3, 9);
        Integer role = GetParam.getParamInteger(req, "role", "Role", -1, 3);

        if (user == null || password == null || confirmPassword == null || role == null) {
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/updateuser.jsp").forward(req, resp);
            return;
        }

        if (!Objects.equals(password, confirmPassword)) {
            req.setAttribute("confirmPasswordErrorMessage", "Password and confirm password are different");
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/updateuser.jsp").forward(req, resp);
            return;
        }
        try {
            userDao.updateAllInformation(user.getFullName(), password, role);
        } catch (Exception ex) {
            Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getRequestDispatcher("UserListController").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
