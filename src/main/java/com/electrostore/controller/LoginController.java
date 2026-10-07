/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.controller;
import com.electrostore.dao.UserDAO;
import com.electrostore.dao.impl.UserDAOImpl;
import com.electrostore.exception.AuthenticationException;
import com.electrostore.model.User;
import com.electrostore.util.PasswordUtil;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class LoginController {
    private final UserDAO userDAO = new UserDAOImpl();

    public User login(String username, String password)
            throws AuthenticationException, SQLException {

        User user = userDAO.authenticate(username, PasswordUtil.hash(password));
        if (user == null) {
            throw new AuthenticationException("Invalid username or password.");
        }
        return user;
    }
    
}
