/*
 * 
 *@author 
 *@author Zaied
 */
package com.gamestore.management;
import com.gamestore.management.Users.*;
import java.util.List;


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