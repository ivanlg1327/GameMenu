package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.models.User;
import com.google.gson.Gson;
import com.unity3d.player.UnityPlayerActivity;

public class UnityBridgeActivity extends AppCompatActivity {
    private User unityUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);
        Gson gson = new Gson();
        this.unityUser = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);

        Gson gson2 = new Gson();
        String myJson = gson2.toJson(this.unityUser);
        Intent i = new Intent(this, UnityPlayerActivity.class);
        i.putExtra("myjson",myJson);
        startActivity(i);
    }
}
