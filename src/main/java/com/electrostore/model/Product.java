/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.model;

/**
 *
 * @author USER
 */
public class Product {
  private int productId;
    private String productCode;
    private String productName;
    private String category;
    private double unitPrice;
    private int stockQuantity; // Removed 'final' so setStockQuantity works
    private String supplier;

    // Default Constructor
    public Product() {}

    // Constructor WITH productId (for retrieving from database)
    public Product(int productId, String productCode, String productName, 
                   String category, double unitPrice, int stockQuantity, String supplier) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.supplier = supplier;
    }

    // Constructor WITHOUT productId (for creating new products before DB insertion)
    public Product(String productCode, String productName, 
                   String category, double unitPrice, int stockQuantity, String supplier) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.supplier = supplier;
    }

    // Getters
    public int getProductId() { return productId; }
    public String getProductCode() { return productCode; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public double getUnitPrice() { return unitPrice; }
    public int getStockQuantity() { return stockQuantity; }
    public String getSupplier() { return supplier; }

    // Setters
    public void setProductId(int productId) { this.productId = productId; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setCategory(String category) { this.category = category; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
} 
    

