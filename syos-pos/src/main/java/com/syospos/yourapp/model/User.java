package com.syospos.yourapp.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;

    // Getter for role
    public String getRole() {
        return role; // Return the role of the user
    }

    // Setter for userId
    public void setUserId(int userId) {
        this.userId = userId; // Set the user ID
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username; // Set the username
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password; // Set the password
    }

    // Setter for role
    public void setRole(String role) {
        this.role = role; // Set the role
    }

    // Getter for password (returning the password for accessing the User object)
    public String getPassword() {
        return password; // Return the password of the user
    }

    // Getter for userId
    public int getUserId() {
        return userId; // Return the user ID
    }

    // Getter for username
    public String getUsername() {
        return username; // Return the username
    }
}
