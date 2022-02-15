package com.pikachu.wallpaper.index.one;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.squareup.picasso.Picasso;

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


//        String clarity = F1RecyclerAdapter.getClarity(s.getSmallUrl());
//        GlideUrl glideUrl = new GlideUrl("https://static-ali.ihansen.org/app/bg1440/VviFtDJakYk.jpg!w400",
//                new LazyHeaders.Builder()
//                .addHeader("Connection","keep-alive")
//                .addHeader("Host", "<calculated when request is sent>")
//                .addHeader("User-Agent","<calculated when request is sent>")
//                .addHeader("Postman-Token","<calculated when request is sent>")
//                .addHeader("User-Agent","PostmanRuntime/7.28.4")
//                .addHeader("Accept-Encoding","gzip, deflate, br")
//                .addHeader("Accept","*/*")
//                .addHeader("Cookie","userid=4953b88d355844c1896b95a17b25a208") //  + LoadUrl.getMessageDigest(clarity)
//                .build());

        //Log.i("TEST_URL", "convert: " + LoadUrl.getMessageDigest(clarity) + "   "+ clarity) ; // cefc5e22d6e0351f843111dcef9cb58f
/*        Glide.with(context)
                .load(glideUrl)
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.m_f1_p_image));*/

        Picasso.get()
                .load(F1RecyclerAdapter.getClarity(s.getSmallUrl()))
                //.rotate(45f)
                //.placeholder(R.drawable.placeholder_disk)
                .into((ImageView) baseViewHolder.getView(R.id.m_f1_p_image));




        baseViewHolder.getView(R.id.m_f1_p_image).setOnClickListener(v -> onTopClickListener.onTopClick( getItemPosition(s) /*baseViewHolder.getAdapterPosition()*//*<= 0 ? 0 : baseViewHolder.getAdapterPosition()-1*/ ,s,list));

        if (isMe){
            String description = s.getInfo().getDescription();
            ((TextView) baseViewHolder.getView(R.id.m_f1_p_text)).setText(description==null||description.equals("")?AppInfo.APP_AUTHOR_NAME:description);
        }
    }
}
