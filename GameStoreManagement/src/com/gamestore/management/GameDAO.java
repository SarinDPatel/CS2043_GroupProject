/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;

//@Author Zaied Ibrahim and AI
public class GameDAO {
private Connection connection;

    public GameDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public void addGame(String title, double price, int discount, int quantity, String description, String warranty,
                        String category, String console) {
        String insertInventorySQL = "INSERT INTO inventory (title, price, discount, quantity_in_stock, description, warranty) VALUES (?, ?, ?, ?, ?, ?)";
        String insertGameSQL = "INSERT INTO games (category, console, inventory_id) VALUES (?, ?, ?)";

        try (PreparedStatement inventoryStmt = connection.prepareStatement(insertInventorySQL, Statement.RETURN_GENERATED_KEYS)) {
            inventoryStmt.setString(1, title);
            inventoryStmt.setDouble(2, price);
            inventoryStmt.setInt(3, discount);
            inventoryStmt.setInt(4, quantity);
            inventoryStmt.setString(5, description);
            inventoryStmt.setString(6, warranty);
            inventoryStmt.executeUpdate();

           
            ResultSet generatedKeys = inventoryStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int inventoryId = generatedKeys.getInt(1);

               
                try (PreparedStatement gameStmt = connection.prepareStatement(insertGameSQL)) {
                    gameStmt.setString(1, category);
                    gameStmt.setString(2, console);
                    gameStmt.setInt(3, inventoryId);
                    gameStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void removeGame(int id) {
        String sql = "DELETE FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void searchForGameByID(int id) {
        String sql = "SELECT g.game_id, i.title, i.price, i.quantity_in_stock FROM games g JOIN inventory i ON g.inventory_id = i.inventory_id WHERE g.game_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Game ID: " + rs.getInt("game_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Price: $" + rs.getDouble("price"));
                System.out.println("Stock: " + rs.getInt("quantity_in_stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void searchForGameByName(String name) {
        String sql = "SELECT g.game_id, i.title, i.price, i.quantity_in_stock FROM games g JOIN inventory i ON g.inventory_id = i.inventory_id WHERE i.title LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Game ID: " + rs.getInt("game_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Price: $" + rs.getDouble("price"));
                System.out.println("Stock: " + rs.getInt("quantity_in_stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
