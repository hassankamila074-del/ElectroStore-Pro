/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USER
 */
public class Sale {
 private int saleId;
    private Integer customerId;
    private double totalAmount;
    private LocalDateTime saleDate;
    private List<SaleItem> items = new ArrayList<>();

    public Sale() {}

    public Sale(Integer customerId, double totalAmount, List<SaleItem> items) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.items = items != null ? items : new ArrayList<>();
    }

    public int getSaleId() { return saleId; }
    public Integer getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public List<SaleItem> getItems() { return items; }

    public void setSaleId(int saleId) { this.saleId = saleId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }
    public void setItems(List<SaleItem> items) { this.items = items; }
}