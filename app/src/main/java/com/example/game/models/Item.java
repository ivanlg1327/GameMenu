package com.example.game.models;

public class Item {
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
