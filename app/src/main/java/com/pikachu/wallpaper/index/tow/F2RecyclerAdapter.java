package com.pikachu.wallpaper.index.tow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;

import java.util.List;


public class F2RecyclerAdapter extends RecyclerView.Adapter<F2RecyclerAdapter.ViewHolder> {


    private final OnItemClickListener onItemClickListener;
    private final Context context;
    private final List<JsonHomeTabsList> jsonHomeTabsLists;


    public interface OnItemClickListener {
        void onItemClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList);
    }


    public F2RecyclerAdapter(Context context, List<JsonHomeTabsList> jsonHomeTabsLists, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.jsonHomeTabsLists = jsonHomeTabsLists;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_home_item1, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        JsonHomeTabsList jsonHomeTabsList = jsonHomeTabsLists.get(position);
        Glide.with(context).load(jsonHomeTabsList.getImageURl()).into(holder.hItem1Image1);
        holder.hItem1Text2.setBackgroundColor(context.getResources().getColor(R.color.black_400));
        holder.hItem1Text1.setText(jsonHomeTabsList.getTabStr());

    }


    @Override
    public int getItemCount() {
        return jsonHomeTabsLists.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView hItem1Image1;
        private final TextView hItem1Text2;
        private final TextView hItem1Text1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hItem1Image1 = itemView.findViewById(R.id.h_item1_image1);
            hItem1Text2 = itemView.findViewById(R.id.h_item1_text2);
            hItem1Text1 = itemView.findViewById(R.id.h_item1_text1);
        }
    }


}
