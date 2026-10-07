/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.model;

/**
 *
 * @author USER
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String status;

    public User() {}

    // Constructor matching UserDAOImpl call: (int userId, String username, String role, int status/other)
    public User(int userId, String username, String role, int statusInt) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.status = String.valueOf(statusInt);
    }

    // Full constructor (5 parameters)
    public User(String username, String password, String fullName, String role, String status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    // Full constructor (6 parameters)
    public User(int userId, String username, String password, String fullName, String role, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

