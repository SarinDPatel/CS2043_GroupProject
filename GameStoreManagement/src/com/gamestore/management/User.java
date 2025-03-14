/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestore.management;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    //Constructor
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Getter
    public int getId() { 
        return id; }
    public String getUsername() { 
        return username; }
    public String getPassword() { 
        return password; }
    public String getEmail() { 
        return email; }

    //Setters
    public void setUsername(String username) { 
        this.username = username; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    // toString() method 
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

