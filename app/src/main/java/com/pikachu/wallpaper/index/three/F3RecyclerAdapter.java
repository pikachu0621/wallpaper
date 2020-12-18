package com.pikachu.wallpaper.index.three;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.index.one.F1RecyclerAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class F3RecyclerAdapter extends BaseQuickAdapter<JsonHomeF1ImageList ,  BaseViewHolder> {


    private Context context;
    private F1RecyclerAdapter.OnItemClickListener onItemClickListener;

    public F3RecyclerAdapter(Context context,List<JsonHomeF1ImageList> data, F1RecyclerAdapter.OnItemClickListener onItemClickListener) {
        super(AppInfo.APP_HOME_F3_ITEM_STYLE ==0 ? R.layout.ui_home_item :
                AppInfo.APP_HOME_F3_ITEM_STYLE == 1 ? R.layout.ui_home_item2 :
                        R.layout.ui_home_item3,data);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }




    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, JsonHomeF1ImageList jsonHomeF1ImageList) {


        ImageView image1 =  baseViewHolder.getView(R.id.h_item_image1);
        ImageView image2 =  baseViewHolder.getView(R.id.h_item_image2);
        ImageView image3 =  baseViewHolder.getView(R.id.h_item_image3);
        TextView textView1 =  baseViewHolder.getView(R.id.h_item_text1);
        TextView textView2 =  baseViewHolder.getView(R.id.h_item_text2);
        TextView textView3 =  baseViewHolder.getView(R.id.h_item_text3);
        RelativeLayout relativeLayout = baseViewHolder.getView(R.id.h_item_relative1);

        // 比例缩小 并设置imageView 高
        F1RecyclerAdapter.proImageHW(context,jsonHomeF1ImageList.getHeight(), AppInfo.APP_HOME_ITEM_PRO, image1);

        Glide.with(context)
                .load(F1RecyclerAdapter.getClarity(jsonHomeF1ImageList.getSmallUrl()))
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(image1);

        String description = jsonHomeF1ImageList.getInfo().getDescription();
        textView1.setText(description==null || description.equals("") ? AppInfo.APP_AUTHOR_NAME :description);
        textView2.setText(jsonHomeF1ImageList.getLikes()+"");
        textView3.setText(jsonHomeF1ImageList.getDownloads()+"");
        relativeLayout.setOnClickListener(v -> onItemClickListener.onItemClick(v,baseViewHolder.getAdapterPosition(),jsonHomeF1ImageList,getData()));
        image2.setOnClickListener(v -> onItemClickListener.onItemDownLoadClick(v,baseViewHolder.getAdapterPosition(),jsonHomeF1ImageList));
        image3.setOnClickListener(v -> onItemClickListener.onItemLikeClick(v,baseViewHolder.getAdapterPosition(),jsonHomeF1ImageList));

    }


    public void addList(List<JsonHomeF1ImageList> jsonHomeF1ImageLists) {
       addData(jsonHomeF1ImageLists);
       notifyDataSetChanged();
    }


}
