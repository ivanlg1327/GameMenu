package com.example.game.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.models.User;
import com.google.gson.Gson;

import java.util.LinkedList;

public class MenuActivity extends GameMenu {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Item a = new Item("Espada","el espadut");
        Item b = new Item ("Escudo", "el escudut");
        Item c = new Item ("Llave", "el llavut");

        LinkedList<Item> its = new LinkedList<Item>();
        its.add(a);
        its.add(b);
        its.add(c);
        this.user = new User("Marc", "Vila","129321416",its);
    }

    public void profileClick(View view){
        Button profile = (Button)view;
        Gson gson = new Gson();
        String myJson = gson.toJson(this.user);

        Intent intentprofile = new Intent(this , ProfileActivity.class);
        intentprofile.putExtra("myjson", myJson);
        startActivity(intentprofile);
    }

    public void shopClick(View view){

        Button info = (Button)view;
        Intent intentshop = new Intent(this , ShopActivity.class);
        startActivity(intentshop);

    }
}
