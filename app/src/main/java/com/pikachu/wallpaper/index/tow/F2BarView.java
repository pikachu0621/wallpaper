package com.pikachu.wallpaper.index.tow;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonBing;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.url.LoadUrl;

import static com.pikachu.wallpaper.util.app.Tools.showToast;

public class F2BarView {


    private final Activity activity;
    private final View view;
    private final F2BarView.onBarImageClick onBarImageClick;
    private View mF2BarView;
    private ImageView mF2BarQmui;
    private TextView mF2BarText;

    private void initView() {
        mF2BarView = view.findViewById(R.id.m_f2_bar_view);
        mF2BarQmui = view.findViewById(R.id.m_f2_bar_qmui);
        mF2BarText = view.findViewById(R.id.m_f2_bar_text);
    }


    public interface onBarImageClick {
        void onClick();
    }


    public F2BarView(Activity activity, onBarImageClick onBarImageClick) {
        this.activity = activity;
        view = LinearLayout.inflate(activity, R.layout.ui_home_f2_bar, null);
        this.onBarImageClick = onBarImageClick;
        initView();
        init();
    }

    private void init() {
        Tools.setNonHigh(activity, mF2BarView);


        //加载每日一图
        new LoadUrl(activity, AppInfo.APP_API_DAY_ONE_IMAGE, new LoadUrl.OnCall() {

            @Override
            public void error(Exception e) {
                showToast(activity, "F2 TabPager" + e.getMessage());
                Log.e(AppInfo.APP_LOG, "F2 TabPager" + e.getMessage());
            }

            @Override
            public void finish(String str) {

                JsonBing jsonBing = new Gson().fromJson(str, JsonBing.class);
                if (jsonBing.getStatus() == 1) {

                    Glide.with(activity)
                            .load(jsonBing.getBing().getUrl())
                            .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                            .into(mF2BarQmui);
                    //mF2BarText.setText(jsonBing.getBing().getCopyright());
                    mF2BarText.setText(jsonBing.getBing().getCopyright());
                }


                mF2BarQmui.setOnClickListener(v -> onBarImageClick.onClick());


            }
        });


    }


    public View getView() {
        return view;
    }


}
