package com.example.game.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.game.R;
import com.example.game.utils.RunnableSprites;

public class LibraryActivity extends AppCompatDialogFragment {

    ImageView img;
    ImageButton next;
    public static Integer[] spritesE = {R.drawable.frame_0,R.drawable.frame_1,R.drawable.frame_2,R.drawable.frame_3,R.drawable.frame_4,R.drawable.frame_5,R.drawable.frame_6,R.drawable.frame_7, R.drawable.frame_8};
    public static Integer[] spritesT = {R.drawable.frame_000,R.drawable.frame_000,R.drawable.frame_000, R.drawable.frame_00,R.drawable.frame_01,R.drawable.frame_02,R.drawable.frame_03,R.drawable.frame_04,R.drawable.frame_05,R.drawable.frame_06,R.drawable.frame_07};
    RunnableSprites myRunnable = new RunnableSprites();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        View view = inf.inflate(R.layout.enemylibrary_layout,null);

        builder.setView(view);
        img = (ImageView) view.findViewById(R.id.imageEnemy);
        next = (ImageButton)view.findViewById(R.id.nextbut);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRunnable.setUpdateInterval(200);
                myRunnable.setSprites(spritesT);
                myRunnable.run();
            }
        });

        myRunnable.setImg(img);
        myRunnable.setSprites(spritesE);
        myRunnable.run();


        DisplayMetrics dm = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int)(width*.7),(int)(height*.6));

        return builder.create();
    }
}

