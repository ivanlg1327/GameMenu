package com.example.game.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String surname;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("objects")
    @Expose
    private List<Item> items;

    public User(String n, String a, String i, List<Item> its){
        this.name = n;
        this.surname = a;
        this.id = i;
        this.items = its;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item it) {items.add(it);}
}
