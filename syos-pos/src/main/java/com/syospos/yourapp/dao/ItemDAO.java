package com.syospos.yourapp.dao;

import com.syospos.yourapp.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Item item) throws SQLException {
        String sql = "INSERT INTO items (item_code, item_name, price, stock, expiry_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getItemCode());
            stmt.setString(2, item.getItemName());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getStock());
            stmt.setDate(5, new java.sql.Date(item.getExpiryDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public Item read(int itemId) throws SQLException {
        String sql = "SELECT * FROM items WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemCode(rs.getString("item_code"));
                item.setItemName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                item.setExpiryDate(rs.getDate("expiry_date"));
                return item;
            }
        }
        return null;
    }

    public List<Item> readAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemCode(rs.getString("item_code"));
                item.setItemName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                item.setExpiryDate(rs.getDate("expiry_date"));
                items.add(item);
            }
        }
        return items;
    }

    public void update(Item item) throws SQLException {
        String sql = "UPDATE items SET item_code = ?, item_name = ?, price = ?, stock = ?, expiry_date = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getItemCode());
            stmt.setString(2, item.getItemName());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getStock());
            stmt.setDate(5, new java.sql.Date(item.getExpiryDate().getTime()));
            stmt.setInt(6, item.getItemId());
            stmt.executeUpdate();
        }
    }

    public void updateStock(String itemCode, int newStock) throws SQLException {
        String sql = "UPDATE items SET stock = ? WHERE item_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setString(2, itemCode);
            stmt.executeUpdate();
        }
    }

    public void delete(int itemId) throws SQLException {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }

    public Item getItemByCode(String itemCode) throws SQLException {
        String sql = "SELECT * FROM items WHERE item_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemCode(rs.getString("item_code"));
                item.setItemName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                item.setExpiryDate(rs.getDate("expiry_date"));
                return item; // Return the found item
            }
        }
        return null; // Return null if no item is found with the given itemCode
    }
}
