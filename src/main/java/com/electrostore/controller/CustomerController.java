/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.controller;
import com.electrostore.dao.CustomerDAO;
import com.electrostore.dao.impl.CustomerDAOImpl;
import com.electrostore.exception.InvalidInputException;
import com.electrostore.model.Customer;
import com.electrostore.util.Validator;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
/**
 *
 * @author USER
 */
public class CustomerController {
    private final CustomerDAO dao = new CustomerDAOImpl();

    private String clean(String s) {
        return s == null ? "" : s.trim();
    }

    private void validate(String name, String phone, String email) throws InvalidInputException {
        Validator.requireText(name, "Customer name");
        Validator.requirePhone(phone);
        Validator.optionalEmail(email);
    }

    public void addCustomer(String name, String phone, String email, String address)
            throws InvalidInputException, SQLException {
        validate(name, phone, email);
        dao.add(new Customer(0, clean(name), clean(phone), clean(email), clean(address), null));
    }

    public void updateCustomer(int id, String name, String phone, String email, String address)
            throws InvalidInputException, SQLException {
        validate(name, phone, email);
        dao.update(new Customer(id, clean(name), clean(phone), clean(email), clean(address), null));
    }

    public void deleteCustomer(int id) throws InvalidInputException, SQLException {
        try {
            dao.delete(id);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new InvalidInputException(
                "This customer has sales records and cannot be deleted.");
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return dao.findAll();
    }

    public List<Customer> searchCustomers(String keyword) throws SQLException {
        return dao.search(clean(keyword));
    }
    
}
