/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;

public class ManagerDAO {
    private Connection connection;

    public ManagerDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
            System.out.println("Database connection success in ManagerDAO.");
        } catch (SQLException e) {
            System.err.println("Database connection failed in ManagerDAO: " + e.getMessage());
            this.connection = null;
        }
    }

    public void addManager(int userId, String name, String email) {
        if (connection == null) {
            System.err.println("Cannot add manager: No database connection.");
            return;
        }

        String sql = "INSERT INTO managers (user_id, name, email) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Manager added: " + name);
        } catch (SQLException e) {
            System.err.println("Error adding manager: " + e.getMessage());
        }
    }

    public void removeManager(int managerId) {
        if (connection == null) {
            System.err.println("Cannot remove manager: No database connection.");
            return;
        }

        String sql = "DELETE FROM managers WHERE manager_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Manager with ID " + managerId + " removed.");
            } else {
                System.out.println("No manager found with ID: " + managerId);
            }
        } catch (SQLException e) {
            System.err.println("Error removing manager: " + e.getMessage());
        }
    }

    public void getManagerById(int managerId) {
        if (connection == null) {
            System.err.println("Cannot get manager by id: No database connection.");
            return;
        }

        String sql = "SELECT * FROM managers WHERE manager_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Manager ID: " + rs.getInt("manager_id"));
                System.out.println("User ID: " + rs.getInt("user_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("No manager found with ID: " + managerId);
            }
        } catch (SQLException e) {
            System.err.println("Error getting manager: " + e.getMessage());
        }
    }
}

