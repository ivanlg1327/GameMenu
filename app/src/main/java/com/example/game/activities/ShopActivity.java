package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.models.User;
import com.example.game.services.apiManager;
import com.example.game.utils.ItemAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity implements ItemAdapter.OnItemListener {

    public ItemAdapter myAdapter;
    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private apiManager aM;
    private User userShop;
    private TextView itemsnum;
    private TextView money;


    List<Item> its;
    List<Item> itsbuyed;

    @Override
    protected void onCreate(Bundle savedIndstanceState) {
        super.onCreate(savedIndstanceState);
        setContentView(R.layout.shop_layout);
        Picasso.get().setLoggingEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclershop);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        itsbuyed = new LinkedList<Item>();

        itemsnum = findViewById(R.id.objetsshoptext);
        money = findViewById(R.id.moneyshoptext);

        Gson gson = new Gson();
        this.userShop = gson.fromJson(getIntent().getStringExtra("myjson"), User.class);
        itemsnum.setText(String.valueOf(userShop.getItems().size()));
        money.setText(String.valueOf(userShop.getMoney()));

        aM = apiManager.getInstance();
        aM.getAllObjects(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    its = response.body();

                    for(int i =0; i < its.size();i++){
                        int finalI = i;
                        if(userShop.getItems().stream().anyMatch(o -> o.getName().equals(its.get(finalI).getName()))){
                            its.remove(its.get(finalI));
                            i = -1;
                        }
                    }
                    myAdapter = new ItemAdapter(ShopActivity.this);
                    myAdapter.setOnItemListener(ShopActivity.this::onItemClick);
                    myAdapter.setItems(its);
                    recyclerView.setAdapter(myAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ShopActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage("Shop not available")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> closeContextMenu());

                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Item itempop = its.get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ShopActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.item_popup, null);

        TextView itemName = (TextView) mView.findViewById(R.id.nameitem);
        TextView itemdescriptor = (TextView) mView.findViewById(R.id.descpitem);
        ImageView itemimg = (ImageView) mView.findViewById(R.id.imgitem);
        TextView atk = (TextView) mView.findViewById(R.id.attackstatbox);
        TextView shy = (TextView) mView.findViewById(R.id.shieldstatbox);
        TextView hp = (TextView) mView.findViewById(R.id.hpstatbox);
        TextView spd = (TextView) mView.findViewById(R.id.speedstatbox);
        Button buybut = (Button) mView.findViewById(R.id.buybutton);
        buybut.setText("Buy for " + String.valueOf(itempop.getPrice()));

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

        if (userShop.getMoney() < 100) {
            buybut.setVisibility(View.INVISIBLE);
        }

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

        buybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userShop.addItem(itempop);
                userShop.buy(itempop.getPrice());
                aM = apiManager.getInstance();
                aM.buyObject(userShop, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response != null) {

                            its.remove(itempop);
                            myAdapter.setItems(its);
                            recyclerView.setAdapter(myAdapter);
                            itemsnum.setText(String.valueOf(userShop.getItems().size()));
                            money.setText(String.valueOf(userShop.getMoney()));

                            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ShopActivity.this);
                            alertDialogBuilder
                                    .setTitle("Success")
                                    .setMessage("You bought: " + itempop.getName())
                                    .setCancelable(true)
                                    .setPositiveButton("OK", (dialog, which) ->  closeContextMenu());

                            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            buybut.setVisibility(View.INVISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ShopActivity.this);
                        alertDialogBuilder
                                .setTitle("Error")
                                .setMessage("Unable to perform request")
                                .setCancelable(true)
                                .setPositiveButton("OK", (dialog, which) ->  finish());

                        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
            }
        });
    }


    public void closeClick(View view){
        Button close = (Button)view;
        Intent data = new Intent();
        // Activity finished ok, return the item
        data.putExtra("LIST", (Serializable) itsbuyed);
        setResult(RESULT_OK, data);
        finish();
    }
}
