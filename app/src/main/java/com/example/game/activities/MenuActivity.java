package com.example.game.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

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
    private Button playBut;
    Animation bounceAnimation;
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
        profilebut = findViewById(R.id.buttonprofile);
        librarybut = findViewById(R.id.buttonLibrary);


        ObjectAnimator animatorY_1 = ObjectAnimator.ofFloat(imagelogo,"y",150f);
        animatorY_1.setDuration(animationduration);
        animatorY_1.setRepeatCount(Animation.INFINITE);
        animatorY_1.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(animatorY_1);
        animatorSet.start();


        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        bounceAnimation.setRepeatCount(Animation.INFINITE);
        profilebut.startAnimation(bounceAnimation);
        librarybut.startAnimation(bounceAnimation);

        playBut = findViewById(R.id.playbut);
        playBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuActivity.this, UnityBridgeActivity.class);
                startActivity(i);
            }
        });

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

    public void settingClick(View view){
        ImageButton set = (ImageButton) view;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.settings_layout,null);

        Button logout = (Button) mView.findViewById(R.id.logoutbut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getApplicationContext().getSharedPreferences("login",0);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(MenuActivity.this,SplashScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
