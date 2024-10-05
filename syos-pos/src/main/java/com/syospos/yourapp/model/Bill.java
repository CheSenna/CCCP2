package com.syospos.yourapp.model;

import java.sql.ResultSet;
import java.util.List;

public class Bill {
    private int billId;
    private double total;
    private List<Item> items; // Assuming items are stored as a List
    private String saleDate; // Add this field for sale date
    private int itemId; // Add this field for item ID

    // Getters and setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSaleDate() {
        return saleDate; // Return the sale date
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate; // Set the sale date
    }

    public int getItemId() {
        return itemId; // Return the item ID
    }

    public void setItemId(int itemId) {
        this.itemId = itemId; // Set the item ID
    }
}
