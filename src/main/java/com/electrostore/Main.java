/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore;
import com.electrostore.view.LoginForm;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;



/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}

  
    

