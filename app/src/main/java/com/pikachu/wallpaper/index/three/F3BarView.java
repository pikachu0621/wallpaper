package com.pikachu.wallpaper.index.three;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonChinaGeography;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.index.one.F1RecyclerAdapter;
import com.pikachu.wallpaper.index.one.ImageAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.to.aboomy.pager2banner.Banner;
import com.to.aboomy.pager2banner.ScaleInTransformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.pikachu.wallpaper.util.app.Tools.showToast;

public class F3BarView {

    private final Activity activity;
    private final View view;
    private final F3BarView.onBarPageClick onBarPageClick;
    private View mF3BarView;
    private Banner banner;



    public interface onBarPageClick {
        void onPageClick();
    }


    public F3BarView(Activity activity, onBarPageClick onBarPageClick) {
        this.activity = activity;
        view = LinearLayout.inflate(activity, R.layout.ui_home_f3_bar, null);
        this.onBarPageClick = onBarPageClick;
        initView();
        init();
    }

    private void init() {
        Tools.setNonHigh(activity,mF3BarView);


        //加载轮播
        new LoadUrl(activity, AppInfo.getUrl(AppInfo.APP_HOME_F3_AUTO_TAG, new Random(new Date().getTime()).nextInt(AppInfo.APP_HOME_F3_AUTO_PAGER), false), new LoadUrl.OnCall() {

            @Override
            public void error(Exception e) {
                showToast(activity, "F3 TabPager" + e.getMessage());
                Log.e(AppInfo.APP_LOG, "F3 TabPager" + e.getMessage());
            }

            @Override
            public void finish(String str) {

                List<JsonHomeF1ImageList> jsonHomeF1ImageLists = new Gson().fromJson(str, new TypeToken<List<JsonHomeF1ImageList>>() {
                }.getType());

                ArrayList<JsonChinaGeography> strings = new ArrayList<>();
                for (JsonHomeF1ImageList jsonHomeF1ImageList : jsonHomeF1ImageLists){
                    JsonChinaGeography jsonChinaGeography = new JsonChinaGeography();
                    String clarity = F1RecyclerAdapter.getClarity(jsonHomeF1ImageList.getSmallUrl());
                    jsonChinaGeography.setUrl(clarity);
                    String description = jsonHomeF1ImageList.getInfo().getDescription();
                    jsonChinaGeography.setTitle(description==null||description.equals("")?AppInfo.APP_AUTHOR_NAME:description);
                    strings.add(jsonChinaGeography);
                }

                //创建adapter
                banner.setAdapter(new ImageAdapter(activity,true, strings));
                banner.setAutoTurningTime(AppInfo.APP_HOME_F3_AUTO_TIME *1000);
                //开启自动无限轮播
                banner.startTurning();
                //设置左右页面露出来的宽度及item与item之间的宽度
                banner.setPageMargin(Tools.dp2px(activity, 20),Tools.dp2px(activity, 20));
                banner.addPageTransformer(new ScaleInTransformer());


            }
        });


    }

    private void initView() {
        mF3BarView = view.findViewById(R.id.m_f3_bar_view);
        banner = view.findViewById(R.id.m_f3_bar_banner);
    }


    public View getView() {
        return view;
    }


    public Banner getBanner() {
        return banner;
    }
}
