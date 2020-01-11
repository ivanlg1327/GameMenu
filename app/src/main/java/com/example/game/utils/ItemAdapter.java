package com.example.game.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
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
import java.util.LinkedList;
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

    public ItemAdapter(Context context) {
        this.items =  new ArrayList<Item>();
        this.context = context;
    }

   public void setOnItemListener(OnItemListener mOnItemListener){
        this.mOnItemListener = mOnItemListener;
   }

   public void setItems(List<Item> itemsx){
        this.items.addAll(itemsx);
        notifyDataSetChanged();
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
        try{

            Picasso.get().load(it.getUrl()).into(holder.img);
        }
        catch (Exception e){
            holder.img.setImageResource(R.drawable.idcard);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}
