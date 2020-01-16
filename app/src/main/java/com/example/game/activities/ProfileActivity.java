package com.example.game.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.models.User;
import com.example.game.utils.ItemAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends MenuActivity implements ItemAdapter.OnItemListener{

    private User user;
    ImageView profileimg;
    TextView nameText;
    TextView dataText;
    TextView numObjectText;
    TextView atkText;
    TextView spdText;
    TextView hpText;
    TextView shyText;

    public ItemAdapter myAdapter;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //private RunnableSprites myRunnable;
    //Integer[] spritesP = {R.drawable.idle_1,R.drawable.idle_2,R.drawable.idle_3,R.drawable.idle_4,R.drawable.idle_5,R.drawable.idle_6,R.drawable.idle_7,R.drawable.idle_8,R.drawable.idle_9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);

        profileimg = findViewById(R.id.profileicon);
        nameText = findViewById(R.id.Name);
        dataText = findViewById(R.id.data);
        numObjectText = findViewById(R.id.objectbox);
        atkText = findViewById(R.id.attackbox);
        spdText = findViewById(R.id.speedbox);
        hpText = findViewById(R.id.hpbox);
        shyText = findViewById(R.id.shieldbox);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerobj);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new ItemAdapter(this);
        myAdapter.setOnItemListener(this);
        myAdapter.setItems(user.getItems());
        recyclerView.setAdapter(myAdapter);

        nameText.setText(user.getName());
        dataText.setText(user.getEmail());
        numObjectText.setText("Objects: " + String.valueOf(user.getItems().size()));
        atkText.setText(String.valueOf(this.user.getAttackStat()));
        hpText.setText(String.valueOf(this.user.getHealthStat()));
        spdText.setText(String.valueOf(this.user.getSpeedStat()));
        shyText.setText(String.valueOf(this.user.getShieldStat()));

    }

    @Override
    public void onItemClick(int position) {

        Item itempop = user.getItems().get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfileActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.item_popup, null);

        TextView itemName = (TextView) mView.findViewById(R.id.nameitem);
        TextView itemdescriptor = (TextView) mView.findViewById(R.id.descpitem);
        ImageView itemimg = (ImageView) mView.findViewById(R.id.imgitem);
        TextView atk = (TextView) mView.findViewById(R.id.attackstatbox);
        TextView shy = (TextView) mView.findViewById(R.id.shieldstatbox);
        TextView hp = (TextView) mView.findViewById(R.id.hpstatbox);
        TextView spd = (TextView) mView.findViewById(R.id.speedstatbox);
        Button buybut = (Button) mView.findViewById(R.id.buybutton);
        buybut.setVisibility(View.INVISIBLE);

        itemName.setText(itempop.getName());
        itemdescriptor.setText(itempop.getDescription());

        String signo = "+";
        atk.setText(signo + String.valueOf(itempop.getAtk()));
        shy.setText(signo + String.valueOf(itempop.getShield()));
        hp.setText(signo + String.valueOf(itempop.getHp()));
        spd.setText(signo + String.valueOf(itempop.getSpd()));
        if (itempop.getShield() < 0 || itempop.getAtk() < 0 || itempop.getSpd() < 0 || itempop.getHp() < 0) {
            if (itempop.getHp() < 0) {
                hp.setText(String.valueOf(itempop.getHp()));
            }
            if (itempop.getSpd() < 0) {
                spd.setText(String.valueOf(itempop.getSpd()));
            }
            if (itempop.getAtk() < 0) {
                atk.setText(String.valueOf(itempop.getAtk()));
            }
            if (itempop.getShield() < 0) {
                shy.setText(String.valueOf(itempop.getShield()));
            }

        }
        Picasso.get().load(itempop.getUrl()).into(itemimg);

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();


    }

    public void closeClick(View view){
        Button close = (Button)view;
        finish();
    }
}
