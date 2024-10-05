package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.SalesItemsDAO;
import com.syospos.yourapp.model.SalesItem;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SalesItemsService {
    private final SalesItemsDAO salesItemDAO;

    public SalesItemsService(Connection connection) {
        this.salesItemDAO = new SalesItemsDAO(connection);
    }

    public void createSalesItem(SalesItem salesItem) throws SQLException {
        salesItemDAO.create(salesItem);
    }

    public SalesItem getSalesItem(int saleItemId) throws SQLException {
        return salesItemDAO.read(saleItemId);
    }

    public List<SalesItem> getAllSalesItems() throws SQLException {
        return salesItemDAO.readAll();
    }

    public void updateSalesItem(SalesItem salesItem) throws SQLException {
        salesItemDAO.update(salesItem);
    }

    public void deleteSalesItem(int saleItemId) throws SQLException {
        salesItemDAO.delete(saleItemId);
    }
}
