package com.gamestore.management;
import java.sql.*;
/**
 * @author Gavin Hosford
 */
public class DatabaseConnectivityTest {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/game_store_db"; 
        String user = "root";  // Use your MySQL username
        String password = "macey";  // Use your MySQL password for the database on mariaDB need to create one first
        //change everything as needed to test it
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
            
            // If connection is successful, print a success message
            System.out.println("Database connected successfully!");

            // Test the connection by executing a simple query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1");  // Simple query to verify the connection

            if (rs.next()) {
                System.out.println("Test query executed successfully! Connection is working.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Please check your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database. Please check your credentials.");
            e.printStackTrace();
        } finally {
            // Close the connection to avoid memory leaks
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed successfully.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}