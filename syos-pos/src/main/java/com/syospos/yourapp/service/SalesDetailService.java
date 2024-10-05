package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.SalesDetailsDAO;
import com.syospos.yourapp.model.SalesDetail;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SalesDetailService {
    private final SalesDetailsDAO salesDetailDAO;

    public SalesDetailService(Connection connection) {
        this.salesDetailDAO = new SalesDetailsDAO(connection);
    }

    public SalesDetail getSalesDetail(int saleId, String itemCode) throws SQLException {
        return salesDetailDAO.read(saleId, itemCode);
    }

    public List<SalesDetail> getAllSalesDetails() throws SQLException {
        return salesDetailDAO.readAll();
    }

    public void createSalesDetail(SalesDetail salesDetail) throws SQLException {
        salesDetailDAO.create(salesDetail);
    }

    public void updateSalesDetail(SalesDetail salesDetail) throws SQLException {
        salesDetailDAO.update(salesDetail);
    }

    public void deleteSalesDetail(int saleId, String itemCode) throws SQLException {
        salesDetailDAO.delete(saleId, itemCode);
    }
}
