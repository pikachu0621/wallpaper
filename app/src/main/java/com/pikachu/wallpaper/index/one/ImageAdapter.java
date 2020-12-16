package com.pikachu.wallpaper.index.one;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonChinaGeography;
import com.pikachu.wallpaper.util.app.AppInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ImageAdapter extends BaseQuickAdapter<JsonChinaGeography, BaseViewHolder> {

    private Context context;
    private boolean isMe;



    public ImageAdapter(Context context, boolean isMe, List<JsonChinaGeography> list) {
        super(isMe?R.layout.ui_image1:R.layout.ui_image,list);
        this.isMe = isMe;
        this.context = context;
    }




    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, JsonChinaGeography s) {

        Glide.with(context)
                .load(s.getUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.m_f1_p_image));
        if (isMe){
            ((TextView) baseViewHolder.getView(R.id.m_f1_p_text)).setText(s.getTitle());
        }
    }
}
