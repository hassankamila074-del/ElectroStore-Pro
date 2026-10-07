/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.controller;
import com.electrostore.dao.ProductDAO;
import com.electrostore.dao.impl.ProductDAOImpl;
import com.electrostore.exception.InvalidInputException;
import com.electrostore.model.Product;
import com.electrostore.util.Validator;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author USER
 */
public class ProductController {
    private final ProductDAO dao = new ProductDAOImpl();

    private String clean(String s) {
        return s == null ? "" : s.trim();
    }

    public void addProduct(String code, String name, String category, String priceStr, String qtyStr, String supplier)
            throws InvalidInputException, SQLException {
        Validator.requireText(code, "Product Code");
        Validator.requireText(name, "Product Name");
        Validator.requireText(category, "Category");
        Validator.requireText(supplier, "Supplier");

        double price = Validator.requirePositiveDouble(priceStr, "Unit Price");
        int qty = Validator.requirePositiveInt(qtyStr, "Stock Quantity");

        dao.add(new Product(0, clean(code), clean(name), clean(category), price, qty, clean(supplier)));
    }

    public void updateProduct(int id, String code, String name, String category, String priceStr, String qtyStr, String supplier)
            throws InvalidInputException, SQLException {
        Validator.requireText(code, "Product Code");
        Validator.requireText(name, "Product Name");
        Validator.requireText(category, "Category");
        Validator.requireText(supplier, "Supplier");

        double price = Validator.requirePositiveDouble(priceStr, "Unit Price");
        int qty = Validator.requirePositiveInt(qtyStr, "Stock Quantity");

        dao.update(new Product(id, clean(code), clean(name), clean(category), price, qty, clean(supplier)));
    }

    public void deleteProduct(int id) throws SQLException {
        dao.delete(id);
    }

    public List<Product> getAllProducts() throws SQLException {
        return dao.findAll();
    }

    public List<Product> searchProducts(String keyword) throws SQLException {
        return dao.search(clean(keyword));
    }
}
