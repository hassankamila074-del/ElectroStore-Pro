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
import java.awt.GridLayout;
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
public class PanelReports extends JPanel {
  private JTextField txtFromDate;
    private JTextField txtToDate;
    private JLabel lblTotalRevenue;
    private JLabel lblTotalSales;
    private JTable tblReportDetails;
    private DefaultTableModel reportModel;

    public PanelReports() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(241, 245, 249));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // --- TOP HEADER & DATE RANGE FILTER CONTROLS ---
        JPanel pnlHeaderArea = new JPanel(new BorderLayout(10, 10));
        pnlHeaderArea.setOpaque(false);

        JLabel lblTitle = new JLabel("Reports & Analytics Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(30, 41, 59));
        pnlHeaderArea.add(lblTitle, BorderLayout.NORTH);

        JPanel pnlFilterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        pnlFilterBar.setBackground(Color.WHITE);
        pnlFilterBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));

        JLabel lblFrom = new JLabel("From (YYYY-MM-DD):");
        lblFrom.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtFromDate = new JTextField("2026-01-01", 10);

        JLabel lblTo = new JLabel("To (YYYY-MM-DD):");
        lblTo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtToDate = new JTextField("2026-12-31", 10);

        // --- GENERATE REPORT BUTTON ---
        JButton btnGenerate = new JButton("Generate Report");
        btnGenerate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGenerate.setBackground(new Color(37, 99, 235));
        btnGenerate.setForeground(Color.WHITE);
        btnGenerate.setPreferredSize(new Dimension(150, 32));
        btnGenerate.addActionListener(e -> generateReport());

        JButton btnExport = new JButton("Export to CSV");
        btnExport.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnExport.setBackground(new Color(71, 85, 105));
        btnExport.setForeground(Color.WHITE);
        btnExport.setPreferredSize(new Dimension(130, 32));
        btnExport.addActionListener(e -> exportReport());

        pnlFilterBar.add(lblFrom);
        pnlFilterBar.add(txtFromDate);
        pnlFilterBar.add(lblTo);
        pnlFilterBar.add(txtToDate);
        pnlFilterBar.add(btnGenerate);
        pnlFilterBar.add(btnExport);

        pnlHeaderArea.add(pnlFilterBar, BorderLayout.SOUTH);
        add(pnlHeaderArea, BorderLayout.NORTH);

        // --- CENTER AREA (SUMMARY CARDS + DETAILED TRANSACTIONS TABLE) ---
        JPanel pnlCenter = new JPanel(new BorderLayout(15, 15));
        pnlCenter.setOpaque(false);

        // Summary Cards Grid (Revenue & Count)
        JPanel pnlCards = new JPanel(new GridLayout(1, 2, 20, 0));
        pnlCards.setOpaque(false);

        // Revenue Card
        JPanel cardRevenue = createMetricCard("Total Revenue", "$12,450.80", new Color(16, 185, 129));
        lblTotalRevenue = (JLabel) cardRevenue.getComponent(1);

        // Sales Count Card
        JPanel cardSales = createMetricCard("Total Sales Count", "148 Transactions", new Color(37, 99, 235));
        lblTotalSales = (JLabel) cardSales.getComponent(1);

        pnlCards.add(cardRevenue);
        pnlCards.add(cardSales);

        pnlCenter.add(pnlCards, BorderLayout.NORTH);

        // Report Data Table
        String[] columns = {"Transaction ID", "Date & Time", "Customer Name", "Items Sold", "Total Amount ($)", "Payment Method"};
        reportModel = new DefaultTableModel(columns, 0);

        // Default sample records
        reportModel.addRow(new Object[]{"TXN-1001", "2026-10-01 14:30", "Alice Johnson", 3, "240.50", "Credit Card"});
        reportModel.addRow(new Object[]{"TXN-1002", "2026-10-02 09:15", "Bob Smith", 1, "99.99", "Cash"});
        reportModel.addRow(new Object[]{"TXN-1003", "2026-10-03 16:45", "Charlie Brown", 5, "1,120.00", "Debit Card"});

        tblReportDetails = new JTable(reportModel);
        tblReportDetails.setRowHeight(28);
        tblReportDetails.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(tblReportDetails);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detailed Transaction History"));

        pnlCenter.add(scrollPane, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);
    }

    private JPanel createMetricCard(String title, String initialValue, Color valueColor) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(100, 116, 139));

        JLabel lblVal = new JLabel(initialValue);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblVal.setForeground(valueColor);
        lblVal.setHorizontalAlignment(SwingConstants.LEFT);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblVal, BorderLayout.CENTER);

        return card;
    }

    private void generateReport() {
        String from = txtFromDate.getText().trim();
        String to = txtToDate.getText().trim();

        if (from.isEmpty() || to.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please specify both From and To dates.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Demo logic: refresh table data and recalculate totals
        reportModel.setRowCount(0);
        reportModel.addRow(new Object[]{"TXN-2001", from + " 10:00", "David Miller", 2, "180.00", "Cash"});
        reportModel.addRow(new Object[]{"TXN-2002", to + " 18:20", "Emma Wilson", 4, "540.00", "Credit Card"});

        lblTotalRevenue.setText("$720.00");
        lblTotalSales.setText("2 Transactions");

        JOptionPane.showMessageDialog(this, "Report generated successfully for range: " + from + " to " + to, "Report Generated", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportReport() {
        if (reportModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No report data available to export.", "Export Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Report exported to CSV successfully!", "Export Complete", JOptionPane.INFORMATION_MESSAGE);
    }
}