package com.syospos.yourapp.controller;

import com.syospos.yourapp.model.Item;
import com.syospos.yourapp.model.User;
import com.syospos.yourapp.service.StockService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/stock")
public class StockController extends HttpServlet {

    private StockService stockService;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        stockService = new StockService(connection);
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

        // Get the item code and quantity sold from the request
        String itemCode = request.getParameter("itemCode");
        int quantitySold = Integer.parseInt(request.getParameter("quantity"));

        try {
            // Fetch the item from the stock
            Item item = stockService.getItemByCode(itemCode);

            if (item != null) {
                // Update the stock after sale
                stockService.updateStock(item, quantitySold);

                // Check if reorder is needed
                boolean reorderNeeded = stockService.isReorderNeeded(item);
                request.setAttribute("reorderNeeded", reorderNeeded);
            } else {
                request.setAttribute("error", "Item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating stock.");
        }

        // Forward to the stock view (JSP page)
        request.getRequestDispatcher("/stock.jsp").forward(request, response);
    }
}
