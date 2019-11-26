package com.example.game;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button logIn = findViewById(R.id.logIn);
        final Button signUp = findViewById(R.id.signUp);
        final View.OnClickListener connectListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                switch (id){
                    case R.id.logIn:
                        //funcion conectar
                        Log.d("newScreen","llego antes de la funcion");
                        menuClick();
                        break;

                    case R.id.signUp:
                        //
                        break;

                }
            }
        };
        logIn.setOnClickListener(connectListener);
        signUp.setOnClickListener(connectListener);
    }
    public void menuClick()
    {
        Intent i = new Intent(this,menu.class);
        Log.d("newScreen","antes el start activity");
        startActivity(i);//no le gusta
        Log.d("newScreen","acabo la funcion");
    }
}
