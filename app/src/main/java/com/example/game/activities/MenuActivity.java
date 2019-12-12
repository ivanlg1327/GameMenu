package com.example.game.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.models.User;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class MenuActivity extends GameMenu {

    private User user;
    private ImageView imagelogo;
    long animationduration = 3000; //ms
    static final int SHOP_ITEM_REQUEST = 1;

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

        imagelogo = findViewById(R.id.imageLogo);
        ObjectAnimator animatorY_1 = ObjectAnimator.ofFloat(imagelogo,"y",150f);
        animatorY_1.setDuration(animationduration);
        animatorY_1.setRepeatCount(Animation.INFINITE);
        animatorY_1.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(animatorY_1);
        animatorSet.start();

    }

    public void profileClick(View view){
        ImageButton profile = (ImageButton) view;
        Gson gson = new Gson();
        String myJson = gson.toJson(this.user);

        Intent intentprofile = new Intent(this , ProfileActivity.class);
        intentprofile.putExtra("myjson", myJson);
        startActivity(intentprofile);
    }

    public void shopClick(View view){

        Button info = (Button)view;
        Intent intentshop = new Intent(this , ShopActivity.class);
        startActivityForResult(intentshop, SHOP_ITEM_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SHOP_ITEM_REQUEST) {
            if (data.hasExtra("LIST")) {
                Intent i = getIntent();
                List<Item> list = new LinkedList<Item>();
                list = (List<Item>) data.getSerializableExtra("LIST");
                for (int p = 0; p < list.size(); p++) {
                    this.user.addItem(list.get(p));
                }
            }
        }
    }
}
