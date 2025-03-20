/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

//@Author Zaied Ibrahim
public class Merch {
    private int merchId;
    private String name;
    private String type;
    private String size; 
    private int quantity;
    private int inventoryId;

    //Constructor
    public Merch(int merchId, String name, String type, String size, int quantity, int inventoryId) {
        this.merchId = merchId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
    }

    // Constructor (withoout id )adding merch
    public Merch(String name, String type, String size, int quantity, int inventoryId) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
    }

    //Accesors and Mutators
    public int getMerchId() { 
        return merchId; }
    public void setMerchId(int merchId) { 
        this.merchId = merchId; 
    }

    public String getName() { 
        return name; }
    public void setName(String name) { 
        this.name = name; 
    }

    public String getType() { 
        return type; }
    public void setType(String type) { 
        this.type = type;
    }

    public String getSize() { 
        return size; }
    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() { 
        return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId; }
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId; 
    }

    // toString() method
    @Override
    public String toString() {
        return "Merch{" +
                "merchId=" + merchId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", inventoryId=" + inventoryId +
                '}';
    }
}
