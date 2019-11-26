package com.example.game.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game.R;
import com.example.game.models.User;
import com.example.game.utils.ItemAdapter;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {

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

        mAdapter = new ItemAdapter(user.getItems());
        recyclerView.setAdapter(mAdapter);

        nameText.setText(user.getName() + " " + user.getSurname());
        dataText.setText(user.getId() + "Objects: " + String.valueOf(user.getItems().size()));

    }
}
