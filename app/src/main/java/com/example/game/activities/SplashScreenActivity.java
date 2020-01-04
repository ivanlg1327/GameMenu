package com.example.game.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.models.User;
import com.example.game.services.userManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences spss;
    private userManager uM;
    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);

        spss = getApplicationContext().getSharedPreferences("login",0);

        String username = spss.getString("usernameKey",null);
        String password = spss.getString("passwordKey",null);

        if(username != null && password != null){
            try{
                uM = userManager.getInstance();
                uM.loginUser(username, new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null) {
                            User responseUser = response.body();
                            if (responseUser.getPass().equals(password) == true) {

                                Intent i = new Intent(SplashScreenActivity.this, MenuActivity.class);
                                Gson gson = new Gson();
                                String myJson = gson.toJson(responseUser);
                                i.putExtra("myjson", myJson);
                                startActivity(i);

                            }

                            else{
                                Intent i = new Intent(SplashScreenActivity.this, GameMenu.class);
                                startActivity(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Intent i = new Intent(SplashScreenActivity.this, GameMenu.class);
                        startActivity(i);
                    }
                });
            }
            catch (Exception e){
                Intent i = new Intent(SplashScreenActivity.this, GameMenu.class);
                startActivity(i);
            }
        }

    }
}
