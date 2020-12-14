package com.pikachu.wallpaper.index.one;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private final Context context;

    public ImageAdapter(Context context, List<String> list) {
        super(R.layout.ui_image,list);
        this.context = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        Glide.with(context).load(s).into((ImageView) baseViewHolder.getView(R.id.m_f1_p_image));
    }
}
