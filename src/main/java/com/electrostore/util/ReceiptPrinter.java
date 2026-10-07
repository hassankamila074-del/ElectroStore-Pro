/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.util;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author USER
 */
public class ReceiptPrinter {
 /**
     * Generates and displays the Sales Receipt report for a given sale ID.
     * 
     * @param saleId The ID of the sale record in the database
     * @param conn   Active JDBC Database Connection
     */
    public static void printReceipt(int saleId, Connection conn) {
        try {
            // 1. Load the compiled .jasper report from resources
            InputStream reportStream = ReceiptPrinter.class.getResourceAsStream("/reports/SalesReport.jasper");

            if (reportStream == null) {
                System.err.println("Error: SalesReport.jasper file not found in /resources/reports/");
                return;
            }

            // 2. Pass the SALE_ID parameter expected by the Jasper SQL query
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("SALE_ID", saleId);

            // 3. Fill the report using data retrieved via JDBC connection
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, conn);

            // 4. Open the print preview dialog (false = don't exit application when viewer is closed)
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }   
}
