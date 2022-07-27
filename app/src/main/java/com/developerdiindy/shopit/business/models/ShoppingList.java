package com.developerdiindy.shopit.business.models;

public class ShoppingList {
    private int ID;
    private String name;

    public ShoppingList(String name){
        this.setName(name);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
