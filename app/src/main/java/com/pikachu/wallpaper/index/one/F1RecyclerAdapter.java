package com.pikachu.wallpaper.index.one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.widget.QMUIRadiusImageView;

import org.greenrobot.greendao.annotation.NotNull;


import java.util.ArrayList;
import java.util.List;


public class F1RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int LAYOUT_TIME = 1,LAYOUT_IMAGE = 2;
    private final OnItemClickListener onItemClickListener;
    private final Context context;
    private List<JsonHomeF1ImageList> jsonHomeF1ImageLists;
    private int itemType ;


    public interface OnItemClickListener{
        void onItemClick(View v,int position,JsonHomeF1ImageList jsonHomeF1ImageList,List<JsonHomeF1ImageList> jsonHomeF1ImageLists);
        void onItemDownLoadClick(View v,int position,JsonHomeF1ImageList jsonHomeF1ImageList);
        void onItemLikeClick(View v,int position,JsonHomeF1ImageList jsonHomeF1ImageList);
    }



    public F1RecyclerAdapter(Context context,int itemType,List<JsonHomeF1ImageList> jsonHomeF1ImageLists, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.itemType = itemType;
        this.jsonHomeF1ImageLists = jsonHomeF1ImageLists;
        this.onItemClickListener = onItemClickListener;
    }






    public void addList(@NotNull List<JsonHomeF1ImageList> jsonHomeF1ImageLists){
        if (this.jsonHomeF1ImageLists == null )
            jsonHomeF1ImageLists = new ArrayList<>();
        this.jsonHomeF1ImageLists.addAll(jsonHomeF1ImageLists);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LAYOUT_TIME)
            return  new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_home_item1, parent, false));
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                itemType ==0 ?
                        R.layout.ui_home_item :
                itemType == 1 ?
                        R.layout.ui_home_item2 :
                        R.layout.ui_home_item3 ,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        JsonHomeF1ImageList jsonHomeF1ImageList = jsonHomeF1ImageLists.get(position);
        if (getItemViewType(position) == LAYOUT_TIME){
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.textView.setText(jsonHomeF1ImageList.getItemTime());
        }else {
            ViewHolder holderImage = (ViewHolder) holder;

            // 比例缩小 并设置imageView 高
            proImageHW(context,jsonHomeF1ImageList.getHeight(), AppInfo.APP_HOME_ITEM_PRO, holderImage.hItemImage1);



            Glide.with(context)
                    .load(getClarity(jsonHomeF1ImageList.getSmallUrl()))
                    //.placeholder(new ColorDrawable(Color.parseColor(jsonHomeF1ImageList.getColor())))android.R.anim.fade_in
                    .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                    //.override((int)(jsonHomeF1ImageList.getWidth()*AppInfo.APP_HOME_ITEM_PRO), (int)(jsonHomeF1ImageList.getHeight()*AppInfo.APP_HOME_ITEM_PRO)) // resizes the image to these dimensions (in pixel). does not respect aspect r
                    .into(holderImage.hItemImage1);


            String description = jsonHomeF1ImageList.getInfo().getDescription();

            holderImage.hItemText1.setText(description==null || description.equals("") ? AppInfo.APP_AUTHOR_NAME :description);
            holderImage.hItemText2.setText(jsonHomeF1ImageList.getLikes()+"");
            holderImage.hItemText3.setText(jsonHomeF1ImageList.getDownloads()+"");
            holderImage.hItemLin1.setOnClickListener(v -> onItemClickListener.onItemClick(v,position,jsonHomeF1ImageList,jsonHomeF1ImageLists ));
            holderImage.hItemImage2.setOnClickListener(v -> onItemClickListener.onItemDownLoadClick(v,position,jsonHomeF1ImageList));
            holderImage.hItemImage3.setOnClickListener(v -> onItemClickListener.onItemLikeClick(v,position,jsonHomeF1ImageList));

        }

    }



    @Override
    public int getItemCount() {
        return jsonHomeF1ImageLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        JsonHomeF1ImageList jsonHomeF1ImageList = jsonHomeF1ImageLists.get(position);
        if (jsonHomeF1ImageList.isTimeItem())
            return LAYOUT_TIME;
        return LAYOUT_IMAGE;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        private final RelativeLayout hItemLin1;
        private final ImageView hItemImage1;
        private final TextView hItemText1;
        private final TextView hItemText2;
        private final TextView hItemText3;
        private final QMUIRadiusImageView hItemImage2;
        private final QMUIRadiusImageView hItemImage3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hItemLin1 = itemView.findViewById(R.id.h_item_relative1);
            hItemImage1 = itemView.findViewById(R.id.h_item_image1);
            hItemText1 = itemView.findViewById(R.id.h_item_text1);
            hItemText2 = itemView.findViewById(R.id.h_item_text2);
            hItemText3 = itemView.findViewById(R.id.h_item_text3);
            hItemImage2 = itemView.findViewById(R.id.h_item_image2);
            hItemImage3 = itemView.findViewById(R.id.h_item_image3);
        }
    }


    public static class ViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView textView;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.h_item1_text1);
        }
    }


    public static void proImageHW(Context context, float h,float pro,ImageView imageView){

        int i = (int) (h * pro);
        //阈值
        if (i < 180)
            i = 180;
        else if (i > 360)
            i = 360;

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = Tools.dp2px(context, i);
    }

    //设置清晰度
    public static String getClarity(String string){
        if (AppInfo.APP_HOME_IMAGE_PX == 0)
            string =  string.replace("!w400","!w600");
        else  if (AppInfo.APP_HOME_IMAGE_PX == 2)
            string =  string.replace("!w400","!o");
        else  if (AppInfo.APP_HOME_IMAGE_PX == 3)
            string =  string.replace("!w400","");
        return string;
    }

}
