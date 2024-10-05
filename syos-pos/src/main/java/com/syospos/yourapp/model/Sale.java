package com.syospos.yourapp.model;

import java.util.Date;

public class Sale {
    private int saleId;
    private Date saleDate;
    private double total;

    // Getters and Setters
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSaleDate(String saleDate) {
    }
}
