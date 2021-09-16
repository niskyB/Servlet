package controller;

import dao.UserDao;
import helper.Router;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "UserListController", urlPatterns = {"/UserListController"})
public class UserListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getHandler(request, response);
            request.getRequestDispatcher(Router.USERLIST).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();

        // Connect to database and get data
        ArrayList<User> userList = userDao.getUserList();

        // Check empty
        if (userList.isEmpty()) {
            request.setAttribute("errorMessage", "The list of users is empty.");
        }

        // Send data
        request.setAttribute("users", userList);
    }

}
