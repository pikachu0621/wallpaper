package com.pikachu.wallpaper.index.tow;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.item.F2ItemData;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class F2RecyclerAdapter extends BaseMultiItemQuickAdapter<F2ItemData, BaseViewHolder> {

    private final Context context;
    private final OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View v, int position, JsonHomeTabsList jsonHomeTabsList);
    }
    public F2RecyclerAdapter(Context context, List<F2ItemData> f2ItemData, OnItemClickListener onItemClickListener) {
        super(f2ItemData);
        this.context = context;
        addItemType(F2ItemData.TEXT, R.layout.ui_text);
        addItemType(F2ItemData.IMAGE,R.layout.ui_home_item1);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, F2ItemData f2ItemData) {




        if (baseViewHolder.getItemViewType() == F2ItemData.TEXT){
            TextView hItem1Text2 = baseViewHolder.getView(R.id.ui_text);
            String tabStr = f2ItemData.getJsonHomeTabsList().getTabStr();
            hItem1Text2.setText(tabStr);
        }else {

            ImageView hItem1Image1= baseViewHolder.getView(R.id.h_item1_image1);
            TextView hItem1Text2 = baseViewHolder.getView(R.id.h_item1_text2);
            TextView hItem1Text1 = baseViewHolder.getView(R.id.h_item1_text1);

           /* Glide.with(context)
                    .load(f2ItemData.getJsonHomeTabsList().getImageURl())
                    .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                    .into(hItem1Image1);*/
            Picasso.get()
                    .load(f2ItemData.getJsonHomeTabsList().getImageURl())
                    .into(hItem1Image1);
            hItem1Text2.setBackgroundColor(context.getResources().getColor(R.color.black_400));
            hItem1Text2.setOnClickListener(v -> onItemClickListener.onItemClick(v,baseViewHolder.getAdapterPosition(),f2ItemData.getJsonHomeTabsList()));
            hItem1Text1.setText(f2ItemData.getJsonHomeTabsList().getTabStr());

        }



    }







}
