/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.controller;
import com.electrostore.dao.UserDAO;
import com.electrostore.dao.impl.UserDAOImpl;
import com.electrostore.model.User;



import java.util.List;
/**
 *
 * @author USER
 */
public class UserController {
  private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public boolean addUser(String username, String password, String fullName, String role, String status) {
        User user = new User(username, password, fullName, role, status);
        return userDAO.addUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // Overload 1: 5 parameters (id, username, fullName, role, status)
    public boolean updateUser(int id, String username, String fullName, String role, String status) {
        User user = new User(id, username, "", fullName, role, status);
        return userDAO.updateUser(user);
    }

    // Overload 2: 4 parameters matching line 223 in PanelUsers.java (id, username, fullName, role)
    public boolean updateUser(int id, String username, String fullName, String role) {
        User user = new User(id, username, "", fullName, role, "ACTIVE");
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    public List<User> searchUsers(String keyword) {
        return userDAO.searchUsers(keyword);
    }
}