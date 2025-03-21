/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

import java.sql.*;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
            System.out.println("EmployeeDAO: Database connection succes.");
        } catch (SQLException e) {
            System.err.println("EmployeeDAO: Database connection failed  " + e.getMessage());
            e.printStackTrace();
            this.connection = null;
        }
    }

    public void addEmployee(int userId, String name, String email, int managerId) {
        if (connection == null) {
            System.err.println("Cannot add employee databse connection faile");
            return;
        }

        String sql = "INSERT INTO employees (user_id, name, email, manager_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setInt(4, managerId);
            stmt.executeUpdate();
            System.out.println("Employee added successfully: " + name);
        } 
            catch (SQLException e) {
            System.err.println("Failed adding adding employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeEmployee(int employeeId) {
        if (connection == null) {
            System.err.println(" Cannot remove employee: the databse connection failed.");
            return;
        }

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Employee removed: ID " + employeeId);
            } 
            else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        } 
            catch (SQLException e) {
            System.err.println("Error removing employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Employee searchEmployeeById(int employeeId) {
        if (connection == null) {
            System.err.println(" Cannot search for employee: database connection failed.");
            return null;
        }

        String sql = "SELECT employee_id, user_id, name, email, manager_id FROM employees WHERE employee_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("manager_id")
                );
            }
            else {
                System.out.println(" No employee found with ID: " + employeeId);
            }
        } 
            catch (SQLException e) {
            System.err.println("failed searching employee by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Employee searchEmployeeByName(String name) {
        if (connection == null) {
            System.err.println("Cannot search for employee: datbase connection failed.");
            return null;
        }

        String sql = "SELECT employee_id, user_id, name, email, manager_id FROM employees WHERE name LIKE ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("manager_id")
                );
            } 
            else {
                System.out.println("No employee found with name: " + name);
            }
        } catch (SQLException e) {
            System.err.println("Error searching employee by name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
