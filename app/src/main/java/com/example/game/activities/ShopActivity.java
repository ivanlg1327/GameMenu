package com.example.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.game.R;
import com.example.game.models.Item;
import com.example.game.utils.ItemAdapter;

import java.util.LinkedList;

public class ShopActivity extends MenuActivity implements ItemAdapter.OnItemListener {

    public ItemAdapter myAdapter;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    LinkedList<Item> its;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclershop);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Item a = new Item("Jetpack","el jetpacut");
        Item b = new Item ("Pistola", "el pistolut");
        Item c = new Item ("Llave", "el llavut");

        its = new LinkedList<Item>();
        its.add(a);
        its.add(b);
        its.add(c);

        mAdapter = new ItemAdapter(its,this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {

        Item itempop = its.get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ShopActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.shopitem_popup,null);

        TextView itemName = (TextView) findViewById(R.id.nameitem);
        TextView itemdescriptor = (TextView) findViewById(R.id.descpitem);
        ImageView itemimg = (ImageView) findViewById(R.id.imgitem);
        Button buybut = (Button) findViewById(R.id.buybutton);

        //itemName.setText(itempop.getName());
        //itemdescriptor.setText(itempop.getDescription());
        /*
        buybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d("TAG", "Buyed:" + itempop.getName());
            }
        });

         */
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
