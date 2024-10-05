package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private final Connection connection;

    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new stock record
    public void create(Stock stock) throws SQLException {
        String sql = "INSERT INTO stock (item_id, batch_number, purchase_date, expiry_date, quantity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getItemId());
            pstmt.setString(2, stock.getBatchNumber());
            pstmt.setDate(3, new java.sql.Date(stock.getPurchaseDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(stock.getExpiryDate().getTime()));
            pstmt.setInt(5, stock.getQuantity());
            pstmt.executeUpdate();
        }
    }

    // Read a stock record by stock_id
    public Stock read(int stockId) throws SQLException {
        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, stockId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Stock stock = new Stock();
                stock.setStockId(rs.getInt("stock_id"));
                stock.setItemId(rs.getInt("item_id"));
                stock.setBatchNumber(rs.getString("batch_number"));
                stock.setPurchaseDate(rs.getDate("purchase_date"));
                stock.setExpiryDate(rs.getDate("expiry_date"));
                stock.setQuantity(rs.getInt("quantity"));
                return stock;
            }
        }
        return null; // or throw an exception if not found
    }

    // Read all stock records
    public List<Stock> readAll() throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stock";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setStockId(rs.getInt("stock_id"));
                stock.setItemId(rs.getInt("item_id"));
                stock.setBatchNumber(rs.getString("batch_number"));
                stock.setPurchaseDate(rs.getDate("purchase_date"));
                stock.setExpiryDate(rs.getDate("expiry_date"));
                stock.setQuantity(rs.getInt("quantity"));
                stocks.add(stock);
            }
        }
        return stocks;
    }

    // Update a stock record
    public void update(Stock stock) throws SQLException {
        String sql = "UPDATE stock SET item_id = ?, batch_number = ?, purchase_date = ?, expiry_date = ?, quantity = ? WHERE stock_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, stock.getItemId());
            pstmt.setString(2, stock.getBatchNumber());
            pstmt.setDate(3, new java.sql.Date(stock.getPurchaseDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(stock.getExpiryDate().getTime()));
            pstmt.setInt(5, stock.getQuantity());
            pstmt.setInt(6, stock.getStockId());
            pstmt.executeUpdate();
        }
    }

    // Delete a stock record by stock_id
    public void delete(int stockId) throws SQLException {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, stockId);
            pstmt.executeUpdate();
        }
    }

    public Stock getStockByItemId(int itemId) throws SQLException {
        Stock stock = null;
        String query = "SELECT * FROM stock WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stock = new Stock();
                stock.setStockId(rs.getInt("stock_id"));
                stock.setItemId(rs.getInt("item_id"));
                stock.setBatchNumber(rs.getString("batch_number"));
                stock.setPurchaseDate(rs.getString("purchase_date"));
                stock.setExpiryDate(rs.getString("expiry_date"));
                stock.setQuantity(rs.getInt("quantity"));
            }
        }
        return stock;
    }
}
