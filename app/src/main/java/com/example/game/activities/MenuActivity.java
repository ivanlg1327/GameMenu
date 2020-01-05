package com.example.game.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    private ImageButton profilebut;
    private ImageButton librarybut;
    private TextView money;
    long animationduration = 3000; //ms
    static final int SHOP_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);

        money = findViewById(R.id.moneytext);
        money.setText(Integer.toString(this.user.getMoney()));


        imagelogo = findViewById(R.id.imageLogo);
        ObjectAnimator animatorY_1 = ObjectAnimator.ofFloat(imagelogo,"y",150f);
        animatorY_1.setDuration(animationduration);
        animatorY_1.setRepeatCount(Animation.INFINITE);
        animatorY_1.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(animatorY_1);
        animatorSet.start();

        /*
        profilebut = findViewById(R.id.buttonprofile);
        ObjectAnimator animatorY_2 = ObjectAnimator.ofFloat(profilebut,"y",10f);
        animatorY_2.setDuration(250);
        animatorY_2.setRepeatCount(Animation.INFINITE);
        animatorY_2.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet_2 = new AnimatorSet();

        animatorSet_2.playTogether(animatorY_2);
        animatorSet_2.start();


        librarybut = findViewById(R.id.buttonLibrary);
        ObjectAnimator animatorY_3 = ObjectAnimator.ofFloat(librarybut,"y",10f);
        animatorY_3.setDuration(250);
        animatorY_3.setRepeatCount(Animation.INFINITE);
        animatorY_3.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet_3 = new AnimatorSet();

        animatorSet_3.playTogether(animatorY_3);
        animatorSet_3.start();
         */


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
        Gson gson = new Gson();
        String myJson = gson.toJson(this.user);

        Intent intentshop = new Intent(this , ShopActivity.class);
        intentshop.putExtra("myjson", myJson);
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

    public void libraryClick(View view) {
        ImageButton lib = (ImageButton) view;
        LibraryActivity libraryActivity = new LibraryActivity();
        libraryActivity.show(getSupportFragmentManager(),"");
    }
}
