package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.SalesItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesItemsDAO {
    private final Connection connection;

    public SalesItemsDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(SalesItem salesItem) throws SQLException {
        String sql = "INSERT INTO sales_items (sale_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, salesItem.getSaleId());
            stmt.setInt(2, salesItem.getItemId());
            stmt.setInt(3, salesItem.getQuantity());
            stmt.setDouble(4, salesItem.getPrice());
            stmt.executeUpdate();
        }
    }

    public SalesItem read(int saleItemId) throws SQLException {
        String sql = "SELECT * FROM sales_items WHERE sale_item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleItemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SalesItem salesItem = new SalesItem();
                salesItem.setSaleItemId(rs.getInt("sale_item_id"));
                salesItem.setSaleId(rs.getInt("sale_id"));
                salesItem.setItemId(rs.getInt("item_id"));
                salesItem.setQuantity(rs.getInt("quantity"));
                salesItem.setPrice(rs.getDouble("price"));
                return salesItem;
            }
        }
        return null;
    }

    public List<SalesItem> readAll() throws SQLException {
        List<SalesItem> salesItems = new ArrayList<>();
        String sql = "SELECT * FROM sales_items";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SalesItem salesItem = new SalesItem();
                salesItem.setSaleItemId(rs.getInt("sale_item_id"));
                salesItem.setSaleId(rs.getInt("sale_id"));
                salesItem.setItemId(rs.getInt("item_id"));
                salesItem.setQuantity(rs.getInt("quantity"));
                salesItem.setPrice(rs.getDouble("price"));
                salesItems.add(salesItem);
            }
        }
        return salesItems;
    }

    public void update(SalesItem salesItem) throws SQLException {
        String sql = "UPDATE sales_items SET quantity = ?, price = ? WHERE sale_item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, salesItem.getQuantity());
            stmt.setDouble(2, salesItem.getPrice());
            stmt.setInt(3, salesItem.getSaleItemId());
            stmt.executeUpdate();
        }
    }

    public void delete(int saleItemId) throws SQLException {
        String sql = "DELETE FROM sales_items WHERE sale_item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleItemId);
            stmt.executeUpdate();
        }
    }
}
