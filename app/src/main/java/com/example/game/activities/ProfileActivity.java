package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game.R;
import com.example.game.models.User;
import com.example.game.utils.ItemAdapter;
import com.google.gson.Gson;

import java.io.Serializable;

public class ProfileActivity extends MenuActivity implements ItemAdapter.OnItemListener{

    private User user;
    ImageView profileimg;
    TextView nameText;
    TextView dataText;

    public ItemAdapter myAdapter;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);

        profileimg = findViewById(R.id.profileicon);
        nameText = findViewById(R.id.Name);
        dataText = findViewById(R.id.data);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerobj);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ItemAdapter(user.getItems(),this);
        recyclerView.setAdapter(mAdapter);

        nameText.setText(user.getName());
        dataText.setText(user.getEmail() + "Objects: " + String.valueOf(user.getItems().size()));

    }

    @Override
    public void onItemClick(int position) {

    }

    public void closeClick(View view){
        Button close = (Button)view;
        finish();
    }
}
