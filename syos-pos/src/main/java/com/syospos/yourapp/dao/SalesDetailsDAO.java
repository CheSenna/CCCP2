package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.SalesDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDetailsDAO {
    private final Connection connection;

    public SalesDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(SalesDetail salesDetail) throws SQLException {
        String sql = "INSERT INTO sales_details (sale_id, item_code, quantity, price_per_item, total_price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, salesDetail.getSaleId());
            stmt.setString(2, salesDetail.getItemCode());
            stmt.setInt(3, salesDetail.getQuantity());
            stmt.setDouble(4, salesDetail.getPricePerItem());
            stmt.setDouble(5, salesDetail.getTotalPrice());
            stmt.executeUpdate();
        }
    }

    public SalesDetail read(int saleId, String itemCode) throws SQLException {
        String sql = "SELECT * FROM sales_details WHERE sale_id = ? AND item_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.setString(2, itemCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SalesDetail salesDetail = new SalesDetail();
                salesDetail.setSaleId(rs.getInt("sale_id"));
                salesDetail.setItemCode(rs.getString("item_code"));
                salesDetail.setQuantity(rs.getInt("quantity"));
                salesDetail.setPricePerItem(rs.getDouble("price_per_item"));
                salesDetail.setTotalPrice(rs.getDouble("total_price"));
                return salesDetail;
            }
        }
        return null;
    }

    public List<SalesDetail> readAll() throws SQLException {
        List<SalesDetail> salesDetails = new ArrayList<>();
        String sql = "SELECT * FROM sales_details";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SalesDetail salesDetail = new SalesDetail();
                salesDetail.setSaleId(rs.getInt("sale_id"));
                salesDetail.setItemCode(rs.getString("item_code"));
                salesDetail.setQuantity(rs.getInt("quantity"));
                salesDetail.setPricePerItem(rs.getDouble("price_per_item"));
                salesDetail.setTotalPrice(rs.getDouble("total_price"));
                salesDetails.add(salesDetail);
            }
        }
        return salesDetails;
    }

    public void update(SalesDetail salesDetail) throws SQLException {
        String sql = "UPDATE sales_details SET quantity = ?, price_per_item = ?, total_price = ? WHERE sale_id = ? AND item_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, salesDetail.getQuantity());
            stmt.setDouble(2, salesDetail.getPricePerItem());
            stmt.setDouble(3, salesDetail.getTotalPrice());
            stmt.setInt(4, salesDetail.getSaleId());
            stmt.setString(5, salesDetail.getItemCode());
            stmt.executeUpdate();
        }
    }

    public void delete(int saleId, String itemCode) throws SQLException {
        String sql = "DELETE FROM sales_details WHERE sale_id = ? AND item_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.setString(2, itemCode);
            stmt.executeUpdate();
        }
    }
}
