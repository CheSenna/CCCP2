package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.StockDAO;
import com.syospos.yourapp.model.Item;
import com.syospos.yourapp.model.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockService {
    private Connection connection;

    private final StockDAO stockDAO;

    public StockService(Connection connection) {
        this.stockDAO = new StockDAO(connection);
    }

    public void createStock(Stock stock) throws SQLException {
        stockDAO.create(stock);
    }

    public Stock getStock(int stockId) throws SQLException {
        return stockDAO.read(stockId);
    }

    public List<Stock> getAllStocks() throws SQLException {
        return stockDAO.readAll();
    }

    public void updateStock(Stock stock) throws SQLException {
        stockDAO.update(stock);
    }

    public void deleteStock(int stockId) throws SQLException {
        stockDAO.delete(stockId);
    }

    public void updateStock(Item item, int quantitySold) throws SQLException {
        Stock stock = stockDAO.getStockByItemId(item.getItemId());
        if (stock != null) {
            int updatedQuantity = stock.getQuantity() - quantitySold;
            stock.setQuantity(updatedQuantity);
            stockDAO.update(stock);
        }
    }

    // Method to check if reorder is needed
    public boolean isReorderNeeded(Item item) throws SQLException {
        Stock stock = stockDAO.getStockByItemId(item.getItemId());
        if (stock != null) {
            // Assuming reorder level is when quantity is below 10
            return stock.getQuantity() < 10;
        }
        return false;
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
