package com.example.game.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.R;
import com.example.game.models.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView img;
        public View layout;
        OnItemListener onItemListener;

        public ViewHolder(View v, OnItemListener onItemListener) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            img = (ImageView) v.findViewById(R.id.iconObject);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
    private Context context;
    private Item item;
    private List<Item> items;
    private OnItemListener mOnItemListener;

    public ItemAdapter(Context context, Item repox) {
        this.item =  repox;
        this.context = context;
    }

    public ItemAdapter(List<Item> itemsx){
        this.items = itemsx;
    }

    public ItemAdapter(List<Item> itemsx, OnItemListener onItemListener,Context context){
        this.items = itemsx;
        this.context = context;
        this.mOnItemListener = onItemListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.object_layout, parent, false);
        ViewHolder vh = new ViewHolder(v,mOnItemListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item it = items.get(position);
        holder.txtHeader.setText(it.getName());
        holder.txtFooter.setText(it.getDescription());

        Picasso.with(context).load(it.getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}
