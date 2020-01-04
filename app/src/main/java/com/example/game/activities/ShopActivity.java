package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.services.apiManager;
import com.example.game.utils.ItemAdapter;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity implements ItemAdapter.OnItemListener {

    public ItemAdapter myAdapter;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private apiManager aM;


    List<Item> its;
    List<Item> itsbuyed;

    @Override
    protected void onCreate(Bundle savedIndstanceState) {
        super.onCreate(savedIndstanceState);
        setContentView(R.layout.shop_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclershop);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        itsbuyed = new LinkedList<Item>();

        aM = apiManager.getInstance();
        aM.getAllObjects(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    its = response.body();
                    mAdapter = new ItemAdapter(its,ShopActivity.this::onItemClick);
                    recyclerView.setAdapter(mAdapter);
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

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Item itempop = its.get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ShopActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.shopitem_popup,null);

        TextView itemName = (TextView) mView.findViewById(R.id.nameitem);
        TextView itemdescriptor = (TextView) mView.findViewById(R.id.descpitem);
        ImageView itemimg = (ImageView) mView.findViewById(R.id.imgitem);
        Button buybut = (Button) mView.findViewById(R.id.buybutton);

        itemName.setText(itempop.getName());
        itemdescriptor.setText(itempop.getDescription());


        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

        buybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d("TAG", "Buyed:" + itempop.getName());
                dialog.cancel();
                itsbuyed.add(itempop);
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
