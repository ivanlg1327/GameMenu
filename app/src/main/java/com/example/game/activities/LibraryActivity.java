package com.example.game.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.game.R;
import com.example.game.models.EnemyClass;
import com.example.game.utils.RunnableSprites;

import java.util.LinkedList;
import java.util.List;

public class LibraryActivity extends AppCompatDialogFragment {

    ImageView img;
    ImageButton next;
    ImageButton back;
    TextView name; TextView description;TextView hp;TextView dmg;TextView spd;
    public static Integer[] spritesE = {R.drawable.frame_0,R.drawable.frame_1,R.drawable.frame_2,R.drawable.frame_3,R.drawable.frame_4,R.drawable.frame_5,R.drawable.frame_6,R.drawable.frame_7, R.drawable.frame_8};
    public static Integer[] spritesT = {R.drawable.frame_000,R.drawable.frame_000,R.drawable.frame_000, R.drawable.frame_00,R.drawable.frame_01,R.drawable.frame_02,R.drawable.frame_03,R.drawable.frame_04,R.drawable.frame_05,R.drawable.frame_06,R.drawable.frame_07};
    public static Integer[] spritesE_2 = {R.drawable.enemy2_0,R.drawable.enemy2_1,R.drawable.enemy2_2,R.drawable.enemy2_3,R.drawable.enemy2_4,R.drawable.enemy2_5,R.drawable.enemy2_6,R.drawable.enemy2_7,R.drawable.enemy2_8};
    public List<Integer[]> spriteList;
    public List<EnemyClass> enemies;
    public static Integer[] enemydescriptions = {R.string.description_alien_1, R.string.description_alien_2, R.string.description_turret};
    public int counter;
    RunnableSprites myRunnable = new RunnableSprites();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        View view = inf.inflate(R.layout.enemylibrary_layout,null);

        name = view.findViewById(R.id.enemynamebox);
        description = view.findViewById(R.id.enemydescription);
        hp = view.findViewById(R.id.healthenemybox);
        dmg = view.findViewById(R.id.damageenemybox);
        spd = view.findViewById(R.id.speedenemybox);

        enemies = new LinkedList<EnemyClass>();
        EnemyClass alien_1 = new EnemyClass("Alien",25,100,100);
        EnemyClass alien_2 = new EnemyClass("Purple Alien",40,100,150);
        EnemyClass turret = new EnemyClass("Turret",50,200,0);
        enemies.add(alien_1); enemies.add(alien_2);enemies.add(turret);

        spriteList = new LinkedList<Integer[]>();
        spriteList.add(spritesE); spriteList.add(spritesE_2); spriteList.add(spritesT);
        counter = 0;

        builder.setView(view);
        img = (ImageView) view.findViewById(R.id.imageEnemy);
        next = (ImageButton)view.findViewById(R.id.nextbut);
        back = (ImageButton) view.findViewById(R.id.backbut);
        myRunnable.setUpdateInterval(100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter + 1;
                myRunnable.setSprites(spriteList.get(counter));
                name.setText(enemies.get(counter).getName());
                description.setText(getString(enemydescriptions[counter]));
                hp.setText(String.valueOf(enemies.get(counter).getHealth()));
                dmg.setText(String.valueOf(enemies.get(counter).getDmg()));
                spd.setText(String.valueOf(enemies.get(counter).getSpeed()));
                if(counter + 1 == spriteList.size()) {
                    next.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.VISIBLE);
                }
                else {
                    next.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter - 1;
                myRunnable.setSprites(spriteList.get(counter));
                name.setText(enemies.get(counter).getName());
                description.setText(getString(enemydescriptions[counter]));
                hp.setText(String.valueOf(enemies.get(counter).getHealth()));
                dmg.setText(String.valueOf(enemies.get(counter).getDmg()));
                spd.setText(String.valueOf(enemies.get(counter).getSpeed()));
                if (counter == 0){
                    back.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
                else{
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }

            }
        });

        myRunnable.setImg(img);
        myRunnable.setSprites(spriteList.get(counter));
        myRunnable.run();
        back.setVisibility(View.INVISIBLE);
        next.setVisibility(View.VISIBLE);
        name.setText(enemies.get(counter).getName());
        description.setText(getString(enemydescriptions[counter]));
        hp.setText(String.valueOf(enemies.get(counter).getHealth()));
        dmg.setText(String.valueOf(enemies.get(counter).getDmg()));
        spd.setText(String.valueOf(enemies.get(counter).getSpeed()));

        return builder.create();
    }
}

