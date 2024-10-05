package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.ItemDAO;
import com.syospos.yourapp.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private final ItemDAO itemDAO;

    public ItemService(Connection connection) {
        this.itemDAO = new ItemDAO(connection);
    }

    public void createItem(Item item) throws SQLException {
        itemDAO.create(item);
    }

    public Item getItem(int itemId) throws SQLException {
        return itemDAO.read(itemId);
    }

    public Item getItemByCode(String itemCode) throws SQLException {
        // Implement this method in the DAO to retrieve item by its code
        return itemDAO.getItemByCode(itemCode);
    }

    public List<Item> getAllItems() throws SQLException {
        return itemDAO.readAll();
    }

    public void updateItem(Item item) throws SQLException {
        itemDAO.update(item);
    }

    public void deleteItem(int itemId) throws SQLException {
        itemDAO.delete(itemId);
    }
}
