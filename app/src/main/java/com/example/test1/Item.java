package com.example.test1;

import java.io.Serializable;

public class Item implements Serializable {
    public static int ID=0;
    private int id;
    String name;
    String price;

    public Item(int ID,String name, String price) {
        this.id=ID;
        this.name = name;
        this.price = price;
        Item.ID++;
    }
    public Item(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}