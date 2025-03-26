/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/game_store_db";
    private static final String USER = "root";  
    private static final String PASSWORD = "Zz66413084--"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" MySQL JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println(" MySQL JDBC Driver not found! Did you add mysql-connector-java.jar?");
            e.printStackTrace();
        }

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(" Connected to: " + connection.getCatalog());
        return connection;
    }
}

