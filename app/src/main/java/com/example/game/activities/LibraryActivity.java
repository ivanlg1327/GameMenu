package com.example.game.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.game.R;

public class LibraryActivity extends AppCompatDialogFragment {

    ImageView img;
    Runnable myRunnable;
    public static Integer[] spritesE = {R.drawable.frame_0,R.drawable.frame_1,R.drawable.frame_2,R.drawable.frame_3,R.drawable.frame_4,R.drawable.frame_5,R.drawable.frame_6,R.drawable.frame_7, R.drawable.frame_8};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        View view = inf.inflate(R.layout.enemylibrary_layout,null);

        builder.setView(view);
        img = (ImageView) view.findViewById(R.id.imageEnemy);


        Runnable myRunnable = new Runnable() {
            int updateInterval = 100; //=one second
            boolean stop = false;
            int i = 0;

            public void stop() {
                this.stop = true;
            }

            @Override
            public void run() {
                try {
                    img.setImageResource(spritesE[i]);
                    i++;
                }
                catch (Exception e){
                    i = 0;
                }

                if(!stop) {
                    img.postDelayed(this, updateInterval);
                }
            }
        };

        myRunnable.run();

        DisplayMetrics dm = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //getWindow().setLayout((int)(width*.7),(int)(height*.6));

        return builder.create();
    }
}

