package com.example.game.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("objeto")
    @Expose
    private String name;
    @SerializedName("propiedades")
    @Expose
    private String description;
    @SerializedName("ataque")
    @Expose
    private int atk;
    @SerializedName("escudo")
    @Expose
    private int shi;
    @SerializedName("speed")
    @Expose
    private int spd;
    @SerializedName("vida")
    @Expose
    private int hp;
    @SerializedName("url")
    @Expose
    private String url;



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

    public int getAtk() {
        return atk;
    }

    public int getHp() {
        return hp;
    }

    public int getShield() {
        return shi;
    }

    public int getSpd() {
        return spd;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setShi(int shi) {
        this.shi = shi;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl(){
        return url;
    }
}
