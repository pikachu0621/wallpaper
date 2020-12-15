package com.pikachu.wallpaper.index.three;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

public class ThreeFragment extends BaseFragment {


    private View inflate;
    private FragmentActivity activity;
    private SmartRefreshLayout mF1RRefreshLayout;
    private RecyclerView mF1RRecycler;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_recycler, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {
        mF1RRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        mF1RRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        mF1RRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        //mF1RRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        //mF1RRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mF1RRefreshLayout.setOnRefreshListener(refreshlayout -> load());
    }

    //加载
    private void load() {

    }





    @Override
    protected void initData() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
        mF1RRefreshLayout.autoRefresh();//自动刷新
        load();
    }

    private void initView() {
        mF1RRefreshLayout = inflate.findViewById(R.id.m_f1_r_refreshLayout);
        mF1RRecycler = inflate.findViewById(R.id.m_f1_r_recycler);
    }

    @Override
    protected void onDisplay() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
    }
}