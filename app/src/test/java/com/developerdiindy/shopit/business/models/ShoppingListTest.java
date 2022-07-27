package com.developerdiindy.shopit.business.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingListTest {

    ShoppingList myShoppingList;

    @BeforeEach
    public void setup(){
        myShoppingList = new ShoppingList("No Name");
    }

    @Test
    public void getID() {
        myShoppingList.setID(001);
        assertEquals(001, myShoppingList.getID());
    }

    @Test
    public void setID() {
        myShoppingList.setID(001);
        myShoppingList.setID(011);
        assertEquals(011, myShoppingList.getID());
    }

    @Test
    public void getName() {
        assertEquals("No Name", myShoppingList.getName());
    }

    @Test
    public void setName() {
        myShoppingList.setName("Friday");
        assertEquals("Friday", myShoppingList.getName());
    }
}