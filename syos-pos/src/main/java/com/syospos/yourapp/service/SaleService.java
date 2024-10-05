package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.SaleDAO;
import com.syospos.yourapp.model.Sale;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SaleService {
    private final SaleDAO saleDAO;

    public SaleService(Connection connection) {
        this.saleDAO = new SaleDAO(connection);
    }

    public void createSale(Sale sale) throws SQLException {
        saleDAO.create(sale);
    }

    public Sale getSale(int saleId) throws SQLException {
        return saleDAO.read(saleId);
    }

    public List<Sale> getAllSales() throws SQLException {
        return saleDAO.readAll();
    }

    public void updateSale(Sale sale) throws SQLException {
        saleDAO.update(sale);
    }

    public void deleteSale(int saleId) throws SQLException {
        saleDAO.delete(saleId);
    }
}
