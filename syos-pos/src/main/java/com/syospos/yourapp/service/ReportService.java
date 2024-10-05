package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.SaleDAO;
import com.syospos.yourapp.model.Sale;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportService {

    private SaleDAO saleDAO;

    public ReportService(Connection connection) {
        this.saleDAO = new SaleDAO(connection);
    }

    public List<Sale> generateDailySalesReport(String saleDate) throws SQLException {
        return saleDAO.getSalesByDate(saleDate);
    }
}
