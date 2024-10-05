package com.syospos.yourapp.controller;

import com.syospos.yourapp.model.User;
import com.syospos.yourapp.service.AuthService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private AuthService authService;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        connection = (Connection) getServletContext().getAttribute("DBConnection");
        authService = new AuthService(connection); // Ensure AuthService accepts a Connection
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Logic for authentication
            User user = authService.authenticate(username, password); // Adjusted to get User
            if (user != null) {
                request.getSession().setAttribute("user", user);
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("adminDashboard.jsp"); // Admin page
                } else {
                    response.sendRedirect("posPage.jsp"); // User page
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error during login", e); // Handle SQL exception
        }
    }

}
