package com.example.game.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;

import com.example.game.R;
import com.example.game.models.User;
import com.example.game.services.apiManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameMenu extends AppCompatActivity {
    //loginActivity

    private EditText textusername;
    private EditText textpassword;
    ProgressDialog progressDialog;
    private apiManager uM;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getApplicationContext().getSharedPreferences("login",0);
        editor = sp.edit();


        this.textusername = findViewById(R.id.usernamebox);
        this.textpassword = findViewById(R.id.passwordbox);

        alertDialogBuilder = new AlertDialog.Builder(GameMenu.this);

        alertDialogBuilder
                .setTitle("Error")
                .setMessage("Wrong username/password, please try again.")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> closeContextMenu());



        final Button logIn = findViewById(R.id.logIn);
        final Button signUp = findViewById(R.id.signUp);
        final View.OnClickListener connectListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id){
                    case R.id.logIn:
                        menuClick(textusername.getText().toString(),textpassword.getText().toString());
                        break;

                    case R.id.signUp:

                        singUpClick();
                        break;

                }
            }
        };
        logIn.setOnClickListener(connectListener);
        signUp.setOnClickListener(connectListener);

    }

    public void menuClick(String username, String pass)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading user...");
        progressDialog.setMessage("Waiting for response");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        uM = apiManager.getInstance();
        if(username != null && pass != null ){
        uM.loginUser(username, new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        progressDialog.setProgress(100);
                        progressDialog.hide();

                        if(response.body() != null) {
                            User responseUser = response.body();

                            if (responseUser.getPass().equals(pass) == true) {

                                editor.putString("usernameKey", responseUser.getName());
                                editor.putString("passwordKey", responseUser.getPass());

                                editor.commit();

                                Intent i = new Intent(GameMenu.this, MenuActivity.class);
                                Gson gson = new Gson();
                                String myJson = gson.toJson(responseUser);
                                i.putExtra("myjson", myJson);
                                startActivity(i);
                            }

                            else
                            {
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        }

                        else {
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        progressDialog.setProgress(100);
                        progressDialog.hide();

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
        }
    }

    public void singUpClick()
    {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

}
