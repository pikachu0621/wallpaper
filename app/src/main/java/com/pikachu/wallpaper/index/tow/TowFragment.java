package com.pikachu.wallpaper.index.tow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;

import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.pikachu.wallpaper.widget.QMUIRadiusImageView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import static com.pikachu.wallpaper.util.app.Tools.showToast;
import static com.pikachu.wallpaper.util.app.Tools.strToTabsObject;


public class TowFragment extends BaseFragment implements F2RecyclerAdapter.OnItemClickListener {


    private View inflate;
    private FragmentActivity activity;
    private AppBarLayout mF2Appbar;
    private View mF2View;
    private QMUIRadiusImageView mF2Qmui;
    private RelativeLayout mF2Text1;
    private SmartRefreshLayout mF1RRefreshLayout;
    private RecyclerView mF1RRecycler;

    public TowFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_tow, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {
        Tools.setNonHigh(activity, mF2View);

        mF1RRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        mF1RRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        mF1RRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        mF1RRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        //mF1RRefreshLayout.setEnablePureScrollMode(true);//是否启用纯滚动模式
        mF1RRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mF1RRefreshLayout.setOnRefreshListener(refreshlayout -> load());

    }

    private void load() {


        //加载标题
        new LoadUrl(activity, AppInfo.APP_API_TABS_LIST, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                showToast(activity, "F2 RecyclerPager" + e.getMessage());
                mF1RRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }

            @Override
            public void finish(String str) {
                List<JsonHomeTabsList> jsonHomeTabsLists = strToTabsObject(str);
                mF1RRecycler.setAdapter(new F2RecyclerAdapter(activity,jsonHomeTabsLists,TowFragment.this));
                mF1RRecycler.setLayoutManager(new GridLayoutManager(activity,AppInfo.APP_HOME_F2_ITEM_NUMBER ));
                mF1RRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
            }
        });




    }


    @Override
    protected void initData() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
        mF1RRefreshLayout.autoRefresh();//自动刷新
        load();
    }

    private void initView() {
        mF2Appbar = inflate.findViewById(R.id.m_f2_appbar);
        mF2View = inflate.findViewById(R.id.m_f2_view);
        mF2Qmui = inflate.findViewById(R.id.m_f2_qmui);
        mF2Text1 = inflate.findViewById(R.id.m_f2_text1);

        mF1RRefreshLayout = inflate.findViewById(R.id.m_f1_r_refreshLayout);
        mF1RRecycler = inflate.findViewById(R.id.m_f1_r_recycler);
    }

    @Override
    protected void onDisplay() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
    }


    //点击事件
    @Override
    public void onItemClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }



}