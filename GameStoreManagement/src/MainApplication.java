/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.util.List;

import employee.User;
import employee.UserDAO;

public class MainApplication {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();


        User newUser = new User(0, "testuser", "password123", "uniziad@example.com");
        userDAO.addUser(newUser);

     
        List<User> users = userDAO.getAllUsers();
        System.out.println("\n All Users:");
        for (User user : users) {
            System.out.println(user);
        }

        User foundUser = userDAO.getUserByUsername("testuser");
        if (foundUser != null) {
            System.out.println("\n User Found: " + foundUser);
        }

        
        userDAO.updateUserEmail("testuser", "uniziad@example.com");

      
        User updatedUser = userDAO.getUserByUsername("testuser");
        System.out.println("\n Updated User: " + updatedUser);

   
        userDAO.deleteUser("testuser");

  
        System.out.println("\n Users after deleting:");
        users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
