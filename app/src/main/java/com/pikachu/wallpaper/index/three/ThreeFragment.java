package com.pikachu.wallpaper.index.three;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.github.mmin18.widget.RealtimeBlurView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.index.one.F1RecyclerAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.to.aboomy.pager2banner.Banner;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class ThreeFragment extends BaseFragment implements F1RecyclerAdapter.OnItemClickListener, F3BarView.onBarPageClick {

    private int page;
    private View inflate;
    private FragmentActivity activity;
    private SmartRefreshLayout mF1RRefreshLayout;
    private RecyclerView mF1RRecycler;
    private F3RecyclerAdapter recyclerAdapter;
    private LinearLayout mF2Lin1;
    private View mF2View;
    private TextView mF2Text1;
    private ImageView mF2Image1;
    private F3BarView f3BarView;
    private StaggeredGridLayoutManager layoutManager;
    private RealtimeBlurView realtimeBlurView;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_three, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @SuppressLint("Range")
    private void init() {
        Tools.setNonHigh(activity, mF2View);
        mF1RRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        mF1RRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        mF1RRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        mF1RRefreshLayout.setOnRefreshListener(refreshlayout -> load(true));
        mF1RRefreshLayout.setOnLoadMoreListener(refreshlayout -> load(false));
        mF1RRefreshLayout.setHeaderMaxDragRate(4);
        mF1RRefreshLayout.setHeaderTriggerRate(1.5F);

        //滑动监听
        setRecyclerSlo();
    }


    private void load(boolean isUpData) {

        if (isUpData) page = new Random(new Date().getTime()).nextInt(AppInfo.APP_HOME_F3_TJ_PAGER);
        else page++;


        new LoadUrl(activity, AppInfo.getUrl("", page, false), new LoadUrl.OnCall() {


            @Override
            public void error(Exception e) {
                //Tools.showToast(activity, "加载书出错");

                if (isUpData)
                    mF1RRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
                else {
                    mF1RRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    page--;
                }

            }

            @Override
            public void finish(String str) {
                //加载成功后 截取需要的字符串

                List<JsonHomeF1ImageList> jsonHomeF1ImageLists = new Gson().fromJson(str, new TypeToken<List<JsonHomeF1ImageList>>() {
                }.getType());

                if (jsonHomeF1ImageLists != null && jsonHomeF1ImageLists.size() > 0) {


                    if (recyclerAdapter == null || isUpData) {

                        recyclerAdapter = new F3RecyclerAdapter(activity, jsonHomeF1ImageLists, ThreeFragment.this);
                        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        //
                        // layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//防止Item切换
                        mF1RRecycler.setLayoutManager(layoutManager);
                        mF1RRecycler.setAdapter(recyclerAdapter);

                        f3BarView = new F3BarView(activity, ThreeFragment.this);
                        //头尾插入
                        recyclerAdapter.addHeaderView(f3BarView.getView(), 0);
                        recyclerAdapter.addHeaderView(new F3SettingsView(activity).getView(), 1);

                    } else
                        recyclerAdapter.addList(jsonHomeF1ImageLists);


                    if (isUpData) mF1RRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else mF1RRefreshLayout.finishLoadMore(true);//结束加载（加载成功）

                } else {
                    if (isUpData) mF1RRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else {
                        page--;
                        mF1RRefreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
                    }
                }

            }
        });

    }


    //re 滑动监听
    private void setRecyclerSlo() {

        mF1RRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                //totalDy -= dy;
                //totalDy = recyclerView.getChildAt(0).getTop();
                int i = recyclerView.computeVerticalScrollOffset();
                if (i <= 255) {
                    Log.d(AppInfo.APP_LOG, "scroll " + dx + " " + dy + " totalDy " + i);
                    realtimeBlurView.setAlpha(i);
                }
                if (i==0){
                    realtimeBlurView.setAlpha(0);
                }
            }
        });
    }


    @Override
    protected void initData() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
        mF1RRefreshLayout.autoRefresh();//自动刷新
        load(true);
    }

    private void initView() {
        mF1RRefreshLayout = inflate.findViewById(R.id.m_f1_r_refreshLayout);
        mF1RRecycler = inflate.findViewById(R.id.m_f1_r_recycler);

        //bar
        mF2Lin1 = inflate.findViewById(R.id.m_f2_lin1);
        mF2View = inflate.findViewById(R.id.m_f2_view);
        mF2Text1 = inflate.findViewById(R.id.m_f2_text1);
        mF2Image1 = inflate.findViewById(R.id.m_f2_image1);
        realtimeBlurView = inflate.findViewById(R.id.m_f2_RealtimeBlurView);

    }


    //fragment 隐藏
    @Override
    protected void onInvisible() {
        if (f3BarView != null) {
            Banner banner = f3BarView.getBanner();
            if (banner != null)
                banner.stopTurning();
        }
    }


    //fragment 再次显示
    @Override
    protected void onDisplay() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
        if (f3BarView != null){
            Banner banner = f3BarView.getBanner();
            if (banner != null)
                banner.startTurning();//开启自动无限轮播
        }

    }


    //item 点击
    @Override
    public void onItemClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //下载点击
    @Override
    public void onItemDownLoadClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //收藏点击
    @Override
    public void onItemLikeClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }


    //点击收藏
    public void onClickKeep(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //点击下载
    public void onClickDownLoad(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //点击历史
    public void onClickHistory(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //点击设置
    public void onClickSettings(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }

    //点击轮播
    @Override
    public void onPageClick() {

    }


    //点击收索
    public void onClickSeek(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {

    }


}