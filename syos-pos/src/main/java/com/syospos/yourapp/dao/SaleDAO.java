package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    private final Connection connection;

    public SaleDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Sale sale) throws SQLException {
        String sql = "INSERT INTO sales (sale_date, total) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(sale.getSaleDate().getTime()));
            stmt.setDouble(2, sale.getTotal());
            stmt.executeUpdate();
        }
    }

    public Sale read(int saleId) throws SQLException {
        String sql = "SELECT * FROM sales WHERE sale_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setSaleDate(rs.getDate("sale_date"));
                sale.setTotal(rs.getDouble("total"));
                return sale;
            }
        }
        return null;
    }

    public List<Sale> readAll() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setSaleDate(rs.getDate("sale_date"));
                sale.setTotal(rs.getDouble("total"));
                sales.add(sale);
            }
        }
        return sales;
    }

    public void update(Sale sale) throws SQLException {
        String sql = "UPDATE sales SET sale_date = ?, total = ? WHERE sale_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(sale.getSaleDate().getTime()));
            stmt.setDouble(2, sale.getTotal());
            stmt.setInt(3, sale.getSaleId());
            stmt.executeUpdate();
        }
    }

    public void delete(int saleId) throws SQLException {
        String sql = "DELETE FROM sales WHERE sale_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, saleId);
            stmt.executeUpdate();
        }
    }

    public List<Sale> getSalesByDate(String saleDate) throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales WHERE sale_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, saleDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setSaleDate(rs.getDate("sale_date"));
                sale.setTotal(rs.getDouble("total"));
                sales.add(sale);
            }
        }
        return sales;
    }
}
