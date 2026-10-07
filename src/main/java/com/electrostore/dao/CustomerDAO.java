/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.dao;
import com.electrostore.model.Customer;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USER
 */
public interface CustomerDAO {
   void add(Customer customer) throws SQLException;
    void update(Customer customer) throws SQLException;
    void delete(int customerId) throws SQLException;
    Customer findById(int customerId) throws SQLException;
    List<Customer> findAll() throws SQLException;
    List<Customer> search(String keyword) throws SQLException; 
    
}
