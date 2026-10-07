/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PanelUsers extends JPanel{
 private JTextField txtSearch;
    private JTable tblUsers;

    public PanelUsers() {
        setLayout(new BorderLayout());
        setBackground(new Color(241, 245, 249));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // --- TOP HEADER & CONTROLS ---
        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        pnlTop.setOpaque(false);

        JLabel lblTitle = new JLabel("User & Staff Management");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 41, 59));
        pnlTop.add(lblTitle, BorderLayout.NORTH);

        JPanel pnlActions = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlActions.setOpaque(false);

        txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(71, 85, 105));
        btnSearch.setForeground(Color.WHITE);

        JButton btnAdd = new JButton("+ Add User");
        btnAdd.setBackground(new Color(37, 99, 235));
        btnAdd.setForeground(Color.WHITE);

        JButton btnEdit = new JButton("Edit User");
        btnEdit.setBackground(new Color(30, 41, 59));
        btnEdit.setForeground(Color.WHITE);

        JButton btnDelete = new JButton("- Delete User");
        btnDelete.setBackground(new Color(239, 68, 68));
        btnDelete.setForeground(Color.WHITE);

        pnlActions.add(txtSearch);
        pnlActions.add(btnSearch);
        pnlActions.add(btnAdd);
        pnlActions.add(btnEdit);
        pnlActions.add(btnDelete);

        pnlTop.add(pnlActions, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        // --- TABLE ---
        String[] columns = {"User ID", "Username", "Role", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Sample data for testing
        model.addRow(new Object[]{1, "admin", "Admin", "Active"});
        model.addRow(new Object[]{2, "cashier1", "Cashier", "Active"});

        tblUsers = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblUsers);
        add(scrollPane, BorderLayout.CENTER);
    }
}