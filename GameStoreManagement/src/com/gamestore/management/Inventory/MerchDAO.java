/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;

// @Author Zaied Ibrahim and AI
public class MerchDAO {
    private Connection connection;

    public MerchDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
            System.out.println("Database connection success in MerchDAO.");
        } catch (SQLException e) {
            System.err.println("Database connection failed in MerchDAO: " + e.getMessage());
            e.printStackTrace();
            this.connection = null; 
        }
    }

    
    public void addMerch(String name, String type, String size, int quantity, double price, String description) {
        if (connection == null) {
            System.err.println("Cannot add merch: No database connection.");
            return;
        }

        String insertInventorySQL = "INSERT INTO inventory (title, price, quantity_in_stock, description) VALUES (?, ?, ?, ?)";
        String insertMerchSQL = "INSERT INTO merch (type, size, inventory_id) VALUES (?, ?, ?)";

        try (PreparedStatement inventoryStmt = connection.prepareStatement(insertInventorySQL, Statement.RETURN_GENERATED_KEYS)) {
            inventoryStmt.setString(1, name);
            inventoryStmt.setDouble(2, price);
            inventoryStmt.setInt(3, quantity);
            inventoryStmt.setString(4, description);
            inventoryStmt.executeUpdate();

            // Retrieve generated inventory_id
            ResultSet generatedKeys = inventoryStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int inventoryId = generatedKeys.getInt(1);

                // Insert into merch table
                try (PreparedStatement merchStmt = connection.prepareStatement(insertMerchSQL)) {
                    merchStmt.setString(1, type);
                    merchStmt.setString(2, size);
                    merchStmt.setInt(3, inventoryId);
                    merchStmt.executeUpdate();
                    System.out.println("Merch added: " + name);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding merch: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeMerch(int merchId) {
        if (connection == null) {
            System.err.println("Cannot remove merch: No database connection.");
            return;
        }

        String sql = "DELETE FROM inventory WHERE inventory_id = (SELECT inventory_id FROM merch WHERE merch_id = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, merchId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(" Merch item with ID " + merchId + " removed successfully.");
            } else {
                System.out.println(" No merch found with ID: " + merchId);
            }
        } catch (SQLException e) {
            System.err.println(" Error removing merch: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Merch searchMerchById(int merchId) {
        if (connection == null) {
            System.err.println(" Cannot search for merch: No database connection.");
            return null;
        }

        String sql = "SELECT m.merch_id, i.title, m.type, m.size, i.quantity_in_stock, i.inventory_id " +
                     "FROM merch m JOIN inventory i ON m.inventory_id = i.inventory_id " +
                     "WHERE m.merch_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, merchId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Merch(
                    rs.getInt("merch_id"),
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getString("size"),
                    rs.getInt("quantity_in_stock"),
                    rs.getInt("inventory_id")
                );
            } else {
                System.out.println("No merch found with ID: " + merchId);
            }
        } catch (SQLException e) {
            System.err.println("Error searching merch by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Merch searchMerchByTitle(String title) {
        if (connection == null) {
            System.err.println("Cannot search for merch: No database connection.");
            return null;
        }

        String sql = "SELECT m.merch_id, i.title, m.type, m.size, i.quantity_in_stock, i.inventory_id " +
                     "FROM merch m JOIN inventory i ON m.inventory_id = i.inventory_id " +
                     "WHERE i.title LIKE ? LIMIT 1"; 

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Merch(
                    rs.getInt("merch_id"),
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getString("size"),
                    rs.getInt("quantity_in_stock"),
                    rs.getInt("inventory_id")
                );
            } else {
                System.out.println("No merch found with name: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error searching merch by name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
