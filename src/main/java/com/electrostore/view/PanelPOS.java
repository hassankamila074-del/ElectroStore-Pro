/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author USER
 */
public class PanelPOS extends JPanel {
 private JTextField txtSearch;
    private JTextField txtQty;
    private JTable tblCart;
    private DefaultTableModel cartModel;
    private JLabel lblTotalValue;

    // Sample product catalog for dynamic rotation when adding items
    private final String[][] sampleCatalog = {
        {"P1001", "Logitech MX Master 3S", "99.99"},
        {"P1002", "Keychron K2 Keyboard", "89.50"},
        {"P1003", "Samsung Odyssey G7 27\"", "450.00"},
        {"P1004", "Sony WH-1000XM5 Headphones", "380.00"},
        {"P1005", "Anker 65W Fast Charger", "35.99"},
        {"P1006", "SanDisk 1TB Portable SSD", "110.00"}
    };
    private int sampleIndex = 0;

    public PanelPOS() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(241, 245, 249));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // --- TOP HEADER ---
        JLabel lblTitle = new JLabel("POS - Point of Sale");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 41, 59));
        add(lblTitle, BorderLayout.NORTH);

        // --- CENTER CONTAINER ---
        JPanel pnlCenter = new JPanel(new BorderLayout(15, 15));
        pnlCenter.setOpaque(false);

        // --- LEFT SECTION (SEARCH & CART TABLE) ---
        JPanel pnlCartArea = new JPanel(new BorderLayout(10, 10));
        pnlCartArea.setOpaque(false);

        // Search Bar & Controls
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlSearch.setBackground(Color.WHITE);
        pnlSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(5, 5, 5, 5)
        ));

        JLabel lblSearch = new JLabel("Product Name / Search:");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));

        txtSearch = new JTextField(15);
        
        JLabel lblQty = new JLabel("Qty:");
        lblQty.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        txtQty = new JTextField("1", 4);

        JButton btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.setBackground(new Color(37, 99, 235));
        btnAddToCart.setForeground(Color.WHITE);
        btnAddToCart.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAddToCart.addActionListener(e -> addSampleItemToCart());

        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);
        pnlSearch.add(lblQty);
        pnlSearch.add(txtQty);
        pnlSearch.add(btnAddToCart);

        pnlCartArea.add(pnlSearch, BorderLayout.NORTH);

        // Cart Table
        String[] columns = {"Product ID", "Code", "Name", "Unit Price ($)", "Qty", "Subtotal ($)"};
        cartModel = new DefaultTableModel(columns, 0);

        tblCart = new JTable(cartModel);
        tblCart.setRowHeight(28);
        tblCart.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scrollCart = new JScrollPane(tblCart);
        pnlCartArea.add(scrollCart, BorderLayout.CENTER);

        pnlCenter.add(pnlCartArea, BorderLayout.CENTER);

        // --- RIGHT SECTION (SUMMARY & CHECKOUT) ---
        JPanel pnlSummary = new JPanel(new GridBagLayout());
        pnlSummary.setPreferredSize(new Dimension(280, 0));
        pnlSummary.setBackground(Color.WHITE);
        pnlSummary.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        JLabel lblSummaryTitle = new JLabel("Order Summary");
        lblSummaryTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSummaryTitle.setForeground(new Color(30, 41, 59));
        gbc.gridy = 0;
        pnlSummary.add(lblSummaryTitle, gbc);

        JLabel lblTotalLabel = new JLabel("Total Amount:");
        lblTotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTotalLabel.setForeground(new Color(100, 116, 139));
        gbc.gridy = 1;
        pnlSummary.add(lblTotalLabel, gbc);

        lblTotalValue = new JLabel("$0.00");
        lblTotalValue.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTotalValue.setForeground(new Color(16, 185, 129));
        lblTotalValue.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridy = 2;
        pnlSummary.add(lblTotalValue, gbc);

        JButton btnRemove = new JButton("Remove Selected Item");
        btnRemove.setBackground(new Color(239, 68, 68));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemove.addActionListener(e -> removeSelectedItem());
        gbc.gridy = 3;
        gbc.insets = new Insets(30, 0, 5, 0);
        pnlSummary.add(btnRemove, gbc);

        JButton btnCheckout = new JButton("Complete Checkout");
        btnCheckout.setBackground(new Color(16, 185, 129));
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCheckout.setPreferredSize(new Dimension(0, 45));
        btnCheckout.addActionListener(e -> completeCheckout());
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 0);
        pnlSummary.add(btnCheckout, gbc);

        pnlCenter.add(pnlSummary, BorderLayout.EAST);
        add(pnlCenter, BorderLayout.CENTER);
    }

    private void addSampleItemToCart() {
        String query = txtSearch.getText().trim();
        int qty = 1;

        try {
            qty = Integer.parseInt(txtQty.getText().trim());
            if (qty <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive quantity.", "Invalid Qty", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String itemCode;
        String itemName;
        double price;

        if (query.isEmpty()) {
            // Pick next item from catalog sample rotation
            String[] sample = sampleCatalog[sampleIndex];
            itemCode = sample[0];
            itemName = sample[1];
            price = Double.parseDouble(sample[2]);
            sampleIndex = (sampleIndex + 1) % sampleCatalog.length; // cycle through
        } else {
            // User typed custom product name
            itemCode = "P" + (1000 + cartModel.getRowCount() + 1);
            itemName = query;
            price = 49.99; // Default fallback price for manual input
        }

        double subtotal = price * qty;

        cartModel.addRow(new Object[]{
            cartModel.getRowCount() + 1,
            itemCode,
            itemName,
            String.format("%.2f", price),
            qty,
            String.format("%.2f", subtotal)
        });

        calculateTotal();
        txtSearch.setText("");
        txtQty.setText("1");
    }

    private void removeSelectedItem() {
        int selectedRow = tblCart.getSelectedRow();
        if (selectedRow != -1) {
            cartModel.removeRow(selectedRow);
            calculateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Select an item from the cart table to remove.", "Selection Required", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            total += Double.parseDouble(cartModel.getValueAt(i, 5).toString());
        }
        lblTotalValue.setText(String.format("$%.2f", total));
    }

    private void completeCheckout() {
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "The cart is empty!", "Checkout Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Payment processed successfully!\nTotal: " + lblTotalValue.getText(), "Success", JOptionPane.INFORMATION_MESSAGE);
        cartModel.setRowCount(0);
        calculateTotal();
    }
}