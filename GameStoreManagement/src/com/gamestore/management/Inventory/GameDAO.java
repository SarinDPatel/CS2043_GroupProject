/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;
import java.util.ArrayList;

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

        try (PreparedStatement inventoryStmt = connection.prepareStatement(insertInventorySQL,
                Statement.RETURN_GENERATED_KEYS)) {
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

    public Game searchForGameByID(int id) {
        String sql = "SELECT i.title, i.price, g.catergory, g.console FROM games g JOIN inventory i ON g.inventory_id = i.inventory_id WHERE i.id LIKE ?";
        Game toReturn = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String category = rs.getString("category");
                ArrayList<String> categories = new ArrayList<>(0).add(category);
                String console = rs.getString("console");
                ArrayList<String> consoles = new ArrayList<>(0).add(console);

                toReturn = new Game(title, price, consoles, categories);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;

    }

    public void searchForGameByTitle(String title) {
        String sql = "SELECT g.game_id, i.title, i.price, i.quantity_in_stock FROM games g JOIN inventory i ON g.inventory_id = i.inventory_id WHERE i.title LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
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

    public ArrayList<Game> fetchAllGames() {
        String sql = "SELECT i.title, i.price, g.catergory, g.console FROM games g JOIN inventory i ON g.inventory_id = i.inventory_id";
        ArrayList<Game> allGames = new ArrayList<>(0);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                int price = rs.getInt("price");
                String category = rs.getString("category");
                ArrayList<String> categories = new ArrayList<>(0).add(category);
                String console = rs.getString("console");
                ArrayList<String> consoles = new ArrayList<>(0).add(console);

                Game g = new Game(title, price, consoles, categories);
                allGames.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGames;

    }

}
