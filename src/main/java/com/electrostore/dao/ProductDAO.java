/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.dao;
import com.electrostore.model.Product;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USER
 */
public interface ProductDAO {
  void add(Product product) throws SQLException;
    void update(Product product) throws SQLException;
    void delete(int productId) throws SQLException;
    Product findById(int productId) throws SQLException;
    Product getById(int productId) throws SQLException; // Alias method for UI panels
    List<Product> findAll() throws SQLException;
    List<Product> search(String keyword) throws SQLException;
}
