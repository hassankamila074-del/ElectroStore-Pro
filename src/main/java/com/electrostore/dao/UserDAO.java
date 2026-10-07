/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.dao;
import com.electrostore.model.User;
import java.util.List;


/**
 *
 * @author USER
 */
public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> searchUsers(String keyword);
    User authenticate(String username, String password);
}