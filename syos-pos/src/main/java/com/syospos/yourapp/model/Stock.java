package com.syospos.yourapp.model;

import java.util.Date;

public class Stock {
    private int stockId; // primary key
    private int itemId; // foreign key
    private String batchNumber;
    private Date purchaseDate;
    private Date expiryDate;
    private int quantity;

    // Getters and Setters
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public java.sql.Date getPurchaseDate() {
        return (java.sql.Date) purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public java.sql.Date getExpiryDate() {
        return (java.sql.Date) expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchaseDate(String purchaseDate) {
    }

    public void setExpiryDate(String expiryDate) {
    }
}
