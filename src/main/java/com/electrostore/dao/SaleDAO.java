/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.dao;
import com.electrostore.exception.InsufficientStockException;
import com.electrostore.model.Sale;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USER
 */
public interface SaleDAO {
  boolean processSale(Sale sale) throws SQLException, InsufficientStockException;
    Sale findById(int saleId) throws SQLException;
    List<Sale> findAll() throws SQLException;
    List<Sale> findByDateRange(String startDate, String endDate) throws SQLException;  
}
