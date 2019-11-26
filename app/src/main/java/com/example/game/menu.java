package com.example.game;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class menu extends GameMenu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("newScreen","llego antes de abrir el el xml");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


}
