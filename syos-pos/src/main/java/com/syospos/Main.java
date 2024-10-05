package com.syospos;

import com.syospos.database.DatabaseManager;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
