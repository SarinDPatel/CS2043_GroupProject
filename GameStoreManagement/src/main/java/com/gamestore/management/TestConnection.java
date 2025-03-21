
package com.gamestore.management;

import java.sql.Connection;


public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println(" Connection to database successful!");
            } else {
                System.out.println(" Connection failed!");
            }
        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

