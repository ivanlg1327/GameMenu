package com.example.game.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class User {
    @SerializedName("username")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("objects")
    @Expose
    public List<Item> items;
    @SerializedName("money")
    @Expose
    public int money;
    @SerializedName("password")
    @Expose
    private String pass;
    @SerializedName("getxPos")
    @Expose
    public int xPos;
    @SerializedName("getyPos")
    @Expose
    public int yPos;
    @SerializedName("getzPos")
    @Expose
    public int zPos;
    @SerializedName("lastMap")
    @Expose
    public int lastmap;


    public User(String n, String e, String p, List<Item> its){
        this.name = n;
        this.email = e;
        this.pass = p;
        this.items = its;
        this.money = 1000;
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

    public String getPass() {
        return pass;
    }

    public int getMoney() {
        return money;
    }

    public void addItem(Item it) {items.add(it);}

    public void generateBasicObjects(){

        Item jet = new Item("Jetpack v1","Your first Jetpack, may seem basic but gets the job done!");
        jet.setAtk(0); jet.setHp(50); jet.setSpd(100); jet.setShi(50);
        jet.setUrl("http://147.83.7.206:8080/static/images/items/jetpacklvl1.png");
        jet.setPrice(0);
        Item suit = new Item("Suit v1","Your first suit, the one your mother gave you a long time ago!");
        suit.setAtk(0); suit.setHp(50); suit.setSpd(0); suit.setShi(50);
        suit.setUrl("http://147.83.7.206:8080/static/images/items/suitlvl1.png");
        suit.setPrice(0);
        Item gun = new Item("Pistol v1","Your first pistol, be careful with it!");
        gun.setAtk(100); gun.setHp(0); gun.setSpd(0); gun.setShi(0);
        gun.setUrl("http://147.83.7.206:8080/static/images/items/pistollvl1.png");
        gun.setPrice(0);

        items.add(jet); items.add(suit); items.add(gun);
        this.money = 1000;

        this.xPos = 0;
        this.yPos = 0;
        this.zPos = 0;
        this.lastmap = 0;
    }

    public int getAttackStat()
    {
        int atk = 0;
        for(int i = 0; i < this.items.size();i++){
            atk = atk + this.items.get(i).getAtk();
        }
        return atk;
    }

    public int getHealthStat()
    {
        int hp = 0;
        for(int i = 0; i < this.items.size();i++){
            hp = hp + this.items.get(i).getHp();
        }
        return hp;
    }

    public int getSpeedStat()
    {
        int spd = 0;
        for(int i = 0; i < this.items.size();i++){
            spd = spd + this.items.get(i).getSpd();
        }
        return spd;
    }

    public int getShieldStat()
    {
        int sh = 0;
        for(int i = 0; i < this.items.size();i++){
            sh = sh + this.items.get(i).getShield();
        }
        return sh;
    }

    public void buy(int price){
        this.money = this.money - price;
        Log.d("BUY","Something has been buyed with price:" + String.valueOf(price));
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
