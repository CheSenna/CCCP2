package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private final Connection connection;

    public BillDAO(Connection connection) {
        this.connection = connection;
    }

    // SimpleDateFormat to handle the saleDate format
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void create(Bill bill) throws SQLException {
        String sql = "INSERT INTO bill (sale_date, total) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Convert saleDate string to java.sql.Date
            try {
                java.util.Date parsedDate = dateFormat.parse(bill.getSaleDate());
                stmt.setDate(1, new java.sql.Date(parsedDate.getTime()));
            } catch (ParseException e) {
                throw new SQLException("Invalid date format for saleDate", e);
            }
            stmt.setDouble(2, bill.getTotal());
            stmt.executeUpdate();
        }
    }

    public Bill read(int billId) throws SQLException {
        String sql = "SELECT * FROM bill WHERE bill_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                // Convert java.sql.Date to String
                bill.setSaleDate(dateFormat.format(rs.getDate("sale_date")));
                bill.setTotal(rs.getDouble("total"));
                return bill;
            }
        }
        return null; // Return null if no matching record found
    }

    public List<Bill> readAll() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                // Convert java.sql.Date to String
                bill.setSaleDate(dateFormat.format(rs.getDate("sale_date")));
                bill.setTotal(rs.getDouble("total"));
                bills.add(bill);
            }
        }
        return bills;
    }

    public void update(Bill bill) throws SQLException {
        String sql = "UPDATE bill SET sale_date = ?, total = ? WHERE bill_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Convert saleDate string to java.sql.Date
            try {
                java.util.Date parsedDate = dateFormat.parse(bill.getSaleDate());
                stmt.setDate(1, new java.sql.Date(parsedDate.getTime()));
            } catch (ParseException e) {
                throw new SQLException("Invalid date format for saleDate", e);
            }
            stmt.setDouble(2, bill.getTotal());
            stmt.setInt(3, bill.getBillId());
            stmt.executeUpdate();
        }
    }

    public void delete(int billId) throws SQLException {
        String sql = "DELETE FROM bill WHERE bill_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            stmt.executeUpdate();
        }
    }
}
