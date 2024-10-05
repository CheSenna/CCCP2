package com.syospos.yourapp.controller;

import com.syospos.yourapp.model.Sale;
import com.syospos.yourapp.model.User;
import com.syospos.yourapp.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/report")
public class ReportController extends HttpServlet {

    private ReportService reportService;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        reportService = new ReportService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check user role
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return; // Stop further processing
        }

        // Forward to the reports.jsp page to display the report form
        request.getRequestDispatcher("/reports.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check user role
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return; // Stop further processing
        }

        // Retrieve the sale date from the request form
        String saleDate = request.getParameter("saleDate");

        try {
            // Generate the report for the selected date
            List<Sale> dailySales = reportService.generateDailySalesReport(saleDate);
            request.setAttribute("dailySales", dailySales);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error generating sales report: " + e.getMessage());
        }

        // Forward to reports.jsp to display the report data
        request.getRequestDispatcher("/reports.jsp").forward(request, response);
    }
}
