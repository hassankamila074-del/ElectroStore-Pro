/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.model;
import java.time.LocalDate;
/**
 *
 * @author USER
 */
public class Customer {
  private int customerId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private LocalDate registeredDate;

    // Default Constructor
    public Customer() {}

    // Full Constructor (with customerId)
    public Customer(int customerId, String name, String phone, String email, String address, LocalDate registeredDate) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registeredDate = registeredDate;
    }

    // Constructor without ID (for new inserts)
    public Customer(String name, String phone, String email, String address, LocalDate registeredDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registeredDate = registeredDate;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getRegisteredDate() { return registeredDate; }
    public void setRegisteredDate(LocalDate registeredDate) { this.registeredDate = registeredDate; }
}