package com.example.game.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.R;

public class GameMenu extends AppCompatActivity {
    //loginActivity

    private EditText textusername;
    private EditText textpassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textusername = findViewById(R.id.usernamebox);
        this.textpassword = findViewById(R.id.passwordbox);

        final Button logIn = findViewById(R.id.logIn);
        final Button signUp = findViewById(R.id.signUp);
        final View.OnClickListener connectListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id){
                    case R.id.logIn:
                        menuClick();
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
    public void menuClick()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading user...");
        progressDialog.setMessage("Waiting for response");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();


        Intent i = new Intent(this,MenuActivity.class);
        startActivity(i);
    }

    public void singUpClick()
    {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
