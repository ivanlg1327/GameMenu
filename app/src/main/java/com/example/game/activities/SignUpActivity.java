package com.example.game.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.game.R;
import com.example.game.models.User;
import com.example.game.services.apiManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends GameMenu{

    EditText username;
    EditText password;
    EditText email;
    ImageView logo;
    EditText paswordconf;
    Button register;
    private User newUser;
    private apiManager uM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        logo = findViewById(R.id.logoviewsignup);
        moveAnimation = AnimationUtils.loadAnimation(this,R.anim.widemove);
        moveAnimation.setRepeatCount(Animation.INFINITE);
        moveAnimation.setRepeatMode(Animation.REVERSE);
        logo.startAnimation(moveAnimation);

        username = findViewById(R.id.usernamebox);
        password = findViewById(R.id.passbox);
        email = findViewById(R.id.mailbox);
        paswordconf = findViewById(R.id.confirmpass);
        register = findViewById(R.id.registerbut);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerClick();
                }
                catch (Exception e){

                }
            }
        });
    }

    private void registerClick() throws IOException{

        if(password.getText().toString().equals(paswordconf.getText().toString()) == true){
            this.newUser = new User(username.getText().toString(),email.getText().toString(),password.getText().toString());
            this.newUser.generateBasicObjects();
            this.newUser.setMoney(1000);

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading user...");
            progressDialog.setMessage("Waiting for server");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();

            uM = apiManager.getInstance();

            uM.createUser(this.newUser, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressDialog.setProgress(100);
                    progressDialog.hide();

                    User responseUser = response.body();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUpActivity.this);

                    alertDialogBuilder
                            .setTitle("Success")
                            .setMessage("Welcome " + responseUser.getName() + "! Your Account has been created!")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> startLogIn());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SignUpActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(t.getMessage())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> closeContextMenu());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });
        }

        else
        {
            Log.d("TAG", "registerClick:" + password.getText().toString() + " not equals " + paswordconf.getText().toString());
        }

    }

    public void closeClick(View view){
        Button close = (Button)view;
        finish();
    }

    public void startLogIn(){

        Intent i = new Intent(SignUpActivity.this,GameMenu.class);
        startActivity(i);
    }

}
