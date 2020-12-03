package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerView2Type2Adapter extends RecyclerView.Adapter<RecyclerView2Type2Adapter.ViewHolder> {

    Context context;
    ArrayList<String> titles;
    ArrayList<String> subtitles;
    ArrayList<String> images;

    public RecyclerView2Type2Adapter(Context context, ArrayList<String> titles, ArrayList<String> subtitles, ArrayList<String> images) {
        this.context = context;
        this.titles = titles;
        this.subtitles = subtitles;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.type2_item_layout, parent, false);
        return new RecyclerView2Type2Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(images.get(position)).into(holder.imageView);
        holder.title2.setText(titles.get(position));
        holder.subtitle.setText(subtitles.get(position));
    }

    @Override
    public int getItemCount() {
        return subtitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title2, subtitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title2 = itemView.findViewById(R.id.title2);
            subtitle = itemView.findViewById(R.id.subtitle);
            imageView = itemView.findViewById(R.id.imageview2);
        }
    }
}
