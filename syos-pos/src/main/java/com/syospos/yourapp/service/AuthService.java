package com.syospos.yourapp.service;

import com.syospos.yourapp.dao.UserDAO;
import com.syospos.yourapp.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public User authenticate(String username, String password) throws SQLException {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
