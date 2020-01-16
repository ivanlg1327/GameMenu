package com.example.game.models;

public class EnemyClass {
    int dmg;
    int health;
    int speed;
    String name;

    public EnemyClass(String name, int dmg, int hp, int spd){
        this.dmg = dmg;
        this.health = hp;
        this.speed = spd;
        this.name = name;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
