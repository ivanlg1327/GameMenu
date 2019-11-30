package com.example.game.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.R;

public class PopItemActivity extends ShopActivity {

        TextView nameitembox;
        TextView descpitembox;
        ImageView imgitem;
        Button buybt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopitem_popup);

        this.imgitem = (ImageView) findViewById(R.id.imgitem);
        this.nameitembox = (TextView) findViewById(R.id.nameitem);
        this.descpitembox = (TextView) findViewById(R.id.descpitem);
        this.buybt = (Button) findViewById(R.id.buybutton);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.2),(int)(height*.6));
    }
}
