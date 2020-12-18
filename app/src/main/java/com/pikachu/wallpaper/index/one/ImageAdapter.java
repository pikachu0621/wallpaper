package com.pikachu.wallpaper.index.one;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.util.app.AppInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ImageAdapter extends BaseQuickAdapter<JsonHomeF1ImageList, BaseViewHolder> {

    private final OnTopClickListener onTopClickListener;
    private final List<JsonHomeF1ImageList> list;
    private Context context;
    private boolean isMe;



    public interface OnTopClickListener{
        void onTopClick(int position,JsonHomeF1ImageList s, List<JsonHomeF1ImageList> list);
    }


    public ImageAdapter(Context context, boolean isMe, List<JsonHomeF1ImageList> list,OnTopClickListener onTopClickListener) {
        super(isMe?R.layout.ui_image1:R.layout.ui_image,list);
        this.list = list;
        this.isMe = isMe;
        this.context = context;
        this.onTopClickListener = onTopClickListener;
    }




    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, JsonHomeF1ImageList s) {



        Glide.with(context)
                .load(F1RecyclerAdapter.getClarity(s.getSmallUrl()))
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.m_f1_p_image));
        baseViewHolder.getView(R.id.m_f1_p_image).setOnClickListener(v -> onTopClickListener.onTopClick( getItemPosition(s) /*baseViewHolder.getAdapterPosition()*//*<= 0 ? 0 : baseViewHolder.getAdapterPosition()-1*/ ,s,list));

        if (isMe){
            ((TextView) baseViewHolder.getView(R.id.m_f1_p_text)).setText(s.getInfo().getDescription());
        }
    }
}
