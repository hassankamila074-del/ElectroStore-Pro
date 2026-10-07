/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class DBConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/electro_store_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Static getInstance() method
    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null || instance.connection == null || instance.connection.isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Instance getter for instance-based calls
    public Connection getConn() {
        return this.connection;
    }

    // Static helper so DBConnection.getConnection() works everywhere without non-static errors
    public static synchronized Connection getConnection() throws SQLException {
        return getInstance().getConn();
    }
} 