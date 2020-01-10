package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.unity3d.player.UnityPlayerActivity;

public class UnityBridgeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);

        Intent i = new Intent(this, UnityPlayerActivity.class);
        startActivity(i);
    }
}
