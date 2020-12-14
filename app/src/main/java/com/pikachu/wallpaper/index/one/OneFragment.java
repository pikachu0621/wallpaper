package com.pikachu.wallpaper.index.one;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.adapter.PagerAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseAppBarStateChangeListener;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.pikachu.wallpaper.widget.QMUIRadiusImageView;
import com.to.aboomy.pager2banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.pikachu.wallpaper.util.app.Tools.showToast;
import static com.pikachu.wallpaper.util.app.Tools.strToTabsObject;


public class OneFragment extends BaseFragment {

    private View inflate;
    private CollapsingToolbarLayout mF1Sada1;
    private View mF1View;
    private QMUIRadiusImageView ivBrandReturn;
    private RelativeLayout mF1Text1;
    private TabLayout mF1Tab1;
    private ViewPager mF1Pager1;
    private Banner banner;
    private FragmentActivity activity;
    private Thread thread;
    private AppBarLayout mF1AppBar;
    private Banner mF1Pager2;


    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_one, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @Override
    protected void initData() {

        //加载标题
        new LoadUrl(activity, AppInfo.APP_API_TABS_LIST, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                showToast(activity, "F1 RecyclerPager" + e.getMessage());
            }

            @Override
            public void finish(String str) {
                List<JsonHomeTabsList> jsonHomeTabsLists = strToTabsObject(str);
                //控制Tab个数
                if (jsonHomeTabsLists.size() > AppInfo.APP_HOME_TAB_NUMBER && AppInfo.APP_HOME_TAB_NUMBER != -1)
                    jsonHomeTabsLists = jsonHomeTabsLists.subList(0, AppInfo.APP_HOME_TAB_NUMBER);

                ArrayList<String> strings = new ArrayList<>();
                ArrayList<Fragment> fragments = new ArrayList<>();

                for (JsonHomeTabsList listTabs : jsonHomeTabsLists) {
                    strings.add(listTabs.getTabStr());
                    fragments.add(new RecyclerFragment(listTabs));
                }

                PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), fragments, strings);
                mF1Tab1.setupWithViewPager(mF1Pager1);
                mF1Pager1.setAdapter(pagerAdapter);


            }
        });


        //加载轮播
        new LoadUrl(activity, AppInfo.getUrl("today", 0, false), new LoadUrl.OnCall() {

            @Override
            public void error(Exception e) {
                showToast(activity, "F1 TabPager" + e.getMessage());
                Log.e(AppInfo.APP_LOG, "F1 TabPager" + e.getMessage());
            }

            @Override
            public void finish(String str) {

                List<JsonHomeF1ImageList> jsonHomeF1ImageLists = new Gson().fromJson(str, new TypeToken<List<JsonHomeF1ImageList>>() {
                }.getType());

                ArrayList<String> strings = new ArrayList<>();
                for (JsonHomeF1ImageList jsonHomeF1ImageList : jsonHomeF1ImageLists)
                    strings.add(jsonHomeF1ImageList.getSmallUrl());
                //创建adapter
                banner.setAdapter(new ImageAdapter(activity, strings));
                banner.setAutoTurningTime(AppInfo.DEFAULT_AUTO_TIME);
                banner.startTurning();//开启自动无限轮播
            }
        });

        //当布局折叠 结束轮播  反正继续
        mF1AppBar.addOnOffsetChangedListener(new BaseAppBarStateChangeListener(){
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
                    banner.startTurning();//开启
                    QMUIStatusBarHelper.setStatusBarDarkMode(activity);
                }else if(state == State.COLLAPSED){
                    //折叠状态
                    banner.stopTurning();
                    QMUIStatusBarHelper.setStatusBarLightMode(activity);
                }
            }
        });

    }


    private void init() {
        Tools.setNonHigh(activity, mF1View);
        //Glide.with(getActivity()).load(url).into(mF1PImage);
        mF1Tab1.setTabMode(TabLayout.MODE_SCROLLABLE);
       /* tab.setTabTextColors(getResources().getColor(R.color.white_50), getResources().getColor(R.color.white));
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));*/
    }

    @Override
    protected void onInvisible() {
        if (banner != null)
            banner.stopTurning();
    }

    @Override
    protected void onDisplay() {
        if (banner != null)
            banner.startTurning();//开启自动无限轮播
    }


    private void initView() {
        mF1Sada1 = inflate.findViewById(R.id.m_f1_sada1); // 折叠布局
        banner = inflate.findViewById(R.id.m_f1_pager2); //
        mF1View = inflate.findViewById(R.id.m_f1_view);
        ivBrandReturn = inflate.findViewById(R.id.iv_brand_return);
        mF1Text1 = inflate.findViewById(R.id.m_f1_text1);
        mF1Tab1 = inflate.findViewById(R.id.m_f1_tab1);
        mF1Pager1 = inflate.findViewById(R.id.m_f1_pager1);
        mF1AppBar = inflate.findViewById(R.id.m_f1_appBar);
    }
}