/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

//@Author Zaied Ibrahim
public class Game {
    private int gameId;
    private String title;
    private double price;
    private int discount;
    private int quantityInStock;
    private String description;
    private String warranty;
    private String category;
    private String console;
    private int inventoryId;

    //Constructor 
    public Game(int gameId, String title, double price, int discount, int quantityInStock, 
                String description, String warranty, String category, String console, int inventoryId) {
        this.gameId = gameId;
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.warranty = warranty;
        this.category = category;
        this.console = console;
        this.inventoryId = inventoryId;
    }

    //  Constructor adding new game
    public Game(String title, double price, int discount, int quantityInStock, 
                String description, String warranty, String category, String console, int inventoryId) {
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.warranty = warranty;
        this.category = category;
        this.console = console;
        this.inventoryId = inventoryId;
    }

    // Accesors and mutators
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    // toString method
    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", discount=" + discount + "%" +
                ", quantityInStock=" + quantityInStock +
                ", description='" + description + '\'' +
                ", warranty='" + warranty + '\'' +
                ", category='" + category + '\'' +
                ", console='" + console + '\'' +
                ", inventoryId=" + inventoryId +
                '}';
    }
}
