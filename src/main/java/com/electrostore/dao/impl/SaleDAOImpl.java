/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.dao.impl;
import com.electrostore.dao.SaleDAO;
import com.electrostore.exception.InsufficientStockException;
import com.electrostore.model.Sale;
import com.electrostore.model.SaleItem;
import com.electrostore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class SaleDAOImpl implements SaleDAO {

    @Override
    public boolean processSale(Sale sale) throws SQLException, InsufficientStockException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // 1. Insert Sale record into 'sale' (singular)
            String saleSql = "INSERT INTO sale (customer_id, total_amount, sale_date) VALUES (?, ?, NOW())";
            int saleId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                if (sale.getCustomerId() != null && sale.getCustomerId() > 0) {
                    stmt.setInt(1, sale.getCustomerId());
                } else {
                    stmt.setNull(1, Types.INTEGER);
                }
                stmt.setDouble(2, sale.getTotalAmount());
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        saleId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            // 2. Insert Items into 'sale_item' and update 'product' stock
            String itemSql = "INSERT INTO sale_item (sale_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
            String updateStockSql = "UPDATE product SET stock_qty = stock_qty - ? WHERE product_id = ? AND stock_qty >= ?";

            try (PreparedStatement itemStmt = conn.prepareStatement(itemSql);
                 PreparedStatement stockStmt = conn.prepareStatement(updateStockSql)) {

                for (SaleItem item : sale.getItems()) {
                    itemStmt.setInt(1, saleId);
                    itemStmt.setInt(2, item.getProductId());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setDouble(4, item.getUnitPrice());
                    itemStmt.setDouble(5, item.getSubtotal());
                    itemStmt.executeUpdate();

                    stockStmt.setInt(1, item.getQuantity());
                    stockStmt.setInt(2, item.getProductId());
                    stockStmt.setInt(3, item.getQuantity());
                    int updated = stockStmt.executeUpdate();

                    if (updated == 0) {
                        conn.rollback();
                        throw new InsufficientStockException("Insufficient stock for product ID: " + item.getProductId());
                    }
                }
            }

            conn.commit(); // Commit transaction
            return true;
        } catch (Exception ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ex instanceof InsufficientStockException) {
                throw (InsufficientStockException) ex;
            }
            if (ex instanceof SQLException) {
                throw (SQLException) ex;
            }
            throw new SQLException("Transaction failed: " + ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Sale findById(int saleId) throws SQLException {
        String sql = "SELECT * FROM sale WHERE sale_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSale(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Sale> findAll() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sale ORDER BY sale_id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                sales.add(mapResultSetToSale(rs));
            }
        }
        return sales;
    }

    @Override
    public List<Sale> findByDateRange(String startDate, String endDate) throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sale WHERE sale_date BETWEEN ? AND ? ORDER BY sale_id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sales.add(mapResultSetToSale(rs));
                }
            }
        }
        return sales;
    }

    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        int saleId = rs.getInt("sale_id");
        int cId = rs.getInt("customer_id");
        Integer customerId = rs.wasNull() ? null : cId;
        double totalAmount = rs.getDouble("total_amount");

        Sale sale = new Sale();
        sale.setSaleId(saleId);
        sale.setCustomerId(customerId);
        sale.setTotalAmount(totalAmount);
        
        return sale;
    }
}