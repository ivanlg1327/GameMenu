package com.example.game.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.game.R;

public class PopItemActivity extends ShopActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopitem_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));
    }
}
