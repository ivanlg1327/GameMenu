package com.example.game.models;


import java.io.Serializable;

public class Item implements Serializable {
    String name;
    String description;

    public Item(String n, String d){
        this.name = n;
        this.description = d;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
