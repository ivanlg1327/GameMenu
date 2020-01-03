package com.example.game.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class User {
    @SerializedName("username")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("objects")
    @Expose
    private List<Item> items;
    @SerializedName("money")
    @Expose
    private int money;
    @SerializedName("password")
    @Expose
    private String pass;

    public User(String n, String e, String p, List<Item> its){
        this.name = n;
        this.email = e;
        this.pass = p;
        this.items = its;
        this.money = 300;
    }

    public User(String n, String e, String p){

        this.name = n;
        this.email = e;
        this.pass = p;
        this.items = new LinkedList<Item>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
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

    public void generateBasicObjects(){

        Item jet = new Item("Jetpack 1","Your first Jetpack, may seem basic but gets the job done!");
        jet.setAtk(0); jet.setHp(50); jet.setSpd(100); jet.setShi(50);
        Item suit = new Item("Suit 1","Your first suit, the one your mother gave you a long time ago!");
        suit.setAtk(0); suit.setHp(50); suit.setSpd(0); suit.setShi(50);
        Item gun = new Item("Pistol 1","Your first pistol, be careful with it!");
        gun.setAtk(100); gun.setHp(0); gun.setSpd(0); gun.setShi(0);

        items.add(jet); items.add(suit); items.add(gun);
        this.money = 300;
    }
}
