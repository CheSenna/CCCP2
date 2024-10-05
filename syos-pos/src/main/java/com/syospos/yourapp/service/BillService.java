package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.BillDAO;
import com.syospos.yourapp.model.Bill;
import com.syospos.yourapp.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class BillService {
    private BillDAO billDAO;
    private Connection connection;

    // Constructor that accepts a connection
    public BillService(Connection connection) {
        this.connection = connection;
        this.billDAO = new BillDAO(connection);
    }

    // Method to create a Bill from an array of Items
    public Bill createBillFromItems(Item[] items) throws SQLException {
        Bill bill = new Bill();
        double total = 0;

        // Calculate total price and populate Bill details
        for (Item item : items) {
            total += item.getPrice() * item.getStock(); // Assuming stock represents quantity
        }

        bill.setTotal(total);
        bill.setItems(Arrays.asList(items)); // Assuming Bill has a method to set items

        // Persist the bill in the database
        billDAO.create(bill);
        return bill;
    }

    public void createBill(Bill bill) throws SQLException {
        billDAO.create(bill);
    }

    public Bill getBill(int billId) throws SQLException {
        return billDAO.read(billId);
    }

    public List<Bill> getAllBills() throws SQLException {
        return billDAO.readAll();
    }

    public void updateBill(Bill bill) throws SQLException {
        billDAO.update(bill);
    }

    public void deleteBill(int billId) throws SQLException {
        billDAO.delete(billId);
    }
}
