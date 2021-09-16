package controller;

import dao.UserDao;
import helper.GetParam;
import helper.Router;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "UpdateUserViewController", urlPatterns = {"/UpdateUserViewController"})
public class UpdateUserViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!getHandler(request, response)) {
                response.sendRedirect("UserListController");
                return;
            }

            doPost(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(Router.UPDATEUSER).forward(request, response);
    }

    protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();

        // Get param
        String userName = GetParam.getParamString(request, "username", "User name", 3, 15);
        User user = userDao.getUserByName(userName);

        // Check param
        if (user == null) {
            request.setAttribute("errorMessage", "The user does not exist.");
            return false;
        }

        request.setAttribute("userName", user.getUserName());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("role", user.getRole());
        return true;
    }

}
