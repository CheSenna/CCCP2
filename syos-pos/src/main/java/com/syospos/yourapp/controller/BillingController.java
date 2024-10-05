package com.syospos.yourapp.controller;

import com.syospos.yourapp.model.Bill;
import com.syospos.yourapp.model.Item;
import com.syospos.yourapp.model.User;
import com.syospos.yourapp.service.BillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/billing")
public class BillingController extends HttpServlet {

    private BillService billService;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        billService = new BillService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check user role
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"user".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return; // Stop further processing
        }

        // Logic to display the billing form or summary
        request.getRequestDispatcher("/billing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Same access control as doGet
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"user".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return; // Stop further processing
        }

        // Retrieve item codes and quantities from the request
        String[] itemCodes = request.getParameterValues("itemCode");
        String[] quantities = request.getParameterValues("quantity");

        if (itemCodes == null || quantities == null || itemCodes.length != quantities.length) {
            request.setAttribute("error", "Invalid items or quantities provided.");
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
            return;
        }

        // Create an array of Item objects
        Item[] items = new Item[itemCodes.length];
        try {
            for (int i = 0; i < itemCodes.length; i++) {
                Item item = new Item();
                item.setItemCode(itemCodes[i]);
                item.setStock(Integer.parseInt(quantities[i]));
                // Fetch price from the database (placeholder logic)
                item.setPrice(fetchItemPrice(itemCodes[i]));
                items[i] = item;
            }

            // Generate the bill using BillService
            Bill bill = billService.createBillFromItems(items);

            // Set the bill object in the request to display in the view
            request.setAttribute("bill", bill);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing sale: " + e.getMessage());
        }

        // Forward to the billing view (JSP page)
        request.getRequestDispatcher("/billing.jsp").forward(request, response);
    }

    private double fetchItemPrice(String itemCode) {
        // Placeholder for actual price fetching logic from the database
        return 10.0; // Replace with actual price fetching logic
    }
}
