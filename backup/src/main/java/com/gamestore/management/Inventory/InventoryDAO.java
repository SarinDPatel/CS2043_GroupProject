/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;

public class InventoryDAO {
    private Connection connection;

    public InventoryDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
            System.out.println("Database connection success in InventoryDAO.");
        } 
        catch (SQLException e) {
            System.err.println("Database connection failed in InventoryDAO: " + e.getMessage());
            e.printStackTrace();
            this.connection = null; 
        }
    }

    public void addInventory(String title, double price, int discount, int quantity, String description, String warranty, String type) {
        if (connection == null) {
            System.err.println("Cannot add inventory: No database connection.");
            return;
        }

        String sql = "INSERT INTO inventory (title, price, discount, quantity_in_stock, description, warranty, type) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setDouble(2, price);
            stmt.setInt(3, discount);
            stmt.setInt(4, quantity);
            stmt.setString(5, description);
            stmt.setString(6, warranty);
            stmt.setString(7, type);
            stmt.executeUpdate();
            System.out.println(" Inventory item has been added: " + title);
        } 
        catch (SQLException e) {
            System.err.println("Errorr in adding item in inventory: " + e.getMessage());
        }
    }

    public void removeInventory(int inventoryId) {
        if (connection == null) {
            System.err.println(" Cannot remove inventory: No database connection.");
            return;
        }

        String sql = "DELETE FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Inventory item removed: ID " + inventoryId);
            } else {
                System.out.println(" No inventory found with ID: " + inventoryId);
            }
        } 
        catch (SQLException e) {
            System.err.println("Error removing inventory: " + e.getMessage());
        }
    }

    public Inventory getInventoryById(int inventoryId) {
        if (connection == null) {
            System.err.println("Cannot get the inventory: No database connection.");
            return null;
        }

        String sql = "SELECT * FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("title"),
                    rs.getDouble("price"),
                    rs.getInt("discount"),
                    rs.getInt("quantity_in_stock"),
                    rs.getString("description"),
                    rs.getString("warranty"),
                    rs.getString("type")
                );
            } 
            else {
                System.out.println(" No inventory found with ID: " + inventoryId);
            }
        } 
        catch (SQLException e) {
            System.err.println(" Error getting inventory: " + e.getMessage());
        }

        return null;
    }
}
