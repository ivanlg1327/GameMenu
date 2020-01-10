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
import com.example.game.utils.RunnableSprites;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MenuActivity extends GameMenu {

    private User user;
    private ImageView imagelogo;
    private ImageButton profilebut;
    private ImageButton librarybut;
    private ImageView bgimg;
    private TextView money;
    private Button playBut;
    Animation bounceAnimation;
    long animationduration = 3000; //ms
    static final int SHOP_ITEM_REQUEST = 1;
    private Integer[] framesbg1 = {R.drawable.framebg_00,R.drawable.framebg_01,R.drawable.framebg_02,R.drawable.framebg_03,R.drawable.framebg_04,R.drawable.framebg_05,R.drawable.framebg_06,R.drawable.framebg_07,R.drawable.framebg_08,R.drawable.framebg_09,R.drawable.framebg_10,R.drawable.framebg_11,R.drawable.framebg_12,R.drawable.framebg_13,R.drawable.framebg_14,R.drawable.framebg_15,R.drawable.framebg_16,R.drawable.framebg_17,R.drawable.framebg_18,R.drawable.framebg_19,R.drawable.framebg_20,R.drawable.framebg_21,R.drawable.framebg_22,R.drawable.framebg_23,R.drawable.framebg_24,R.drawable.framebg_25,R.drawable.framebg_26,R.drawable.framebg_27,R.drawable.framebg_28,R.drawable.framebg_29,R.drawable.framebg_30,R.drawable.framebg_31,R.drawable.framebg_32,R.drawable.framebg_33,R.drawable.framebg_34,R.drawable.framebg_35,R.drawable.framebg_36,R.drawable.framebg_37,R.drawable.framebg_38,R.drawable.framebg_39,R.drawable.framebg_40,R.drawable.framebg_41,R.drawable.framebg_42,R.drawable.framebg_43,R.drawable.framebg_44,R.drawable.framebg_45,R.drawable.framebg_46,R.drawable.framebg_47,R.drawable.framebg_48,R.drawable.framebg_49,R.drawable.framebg_50,R.drawable.framebg_51,R.drawable.framebg_52,R.drawable.framebg_53,R.drawable.framebg_54,R.drawable.framebg_55,R.drawable.framebg_56,R.drawable.framebg_57,R.drawable.framebg_58,R.drawable.framebg_59,R.drawable.framebg_60,R.drawable.framebg_61,R.drawable.framebg_62,R.drawable.framebg_63,R.drawable.framebg_64,R.drawable.framebg_65,R.drawable.framebg_66,R.drawable.framebg_67,R.drawable.framebg_68,R.drawable.framebg_69,R.drawable.framebg_70};
    private RunnableSprites myRunBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        bgimg = findViewById(R.id.backgroundimg);
        int size = framesbg1.length;
        Integer[] framesbg2 = new Integer[size];
        int j = size;
        for (int i = 0; i < size; i++) {
            framesbg2[j - 1] = framesbg1[i];
            j = j - 1;
        }
        Integer[] framesbg = Stream.concat(Arrays.stream(framesbg1), Arrays.stream(framesbg2))
                .toArray(Integer[]::new);

        myRunBG = new RunnableSprites();
        myRunBG.setImg(bgimg);
        myRunBG.setSprites(framesbg);
        myRunBG.setUpdateInterval(50);
        myRunBG.run();

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
