package com.pikachu.wallpaper.index.one;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import static com.pikachu.wallpaper.util.app.Tools.getItem;
import static com.pikachu.wallpaper.util.app.Tools.showToast;


public class RecyclerFragment extends BaseFragment implements F1RecyclerAdapter.OnItemClickListener {

    private final JsonHomeTabsList jsonHomeTabsList;
    private int minPager = 0;
    private View inflate;
    private SmartRefreshLayout mF1RRefreshLayout;
    private RecyclerView mF1RRecycler;
    private FragmentActivity activity;
    private int page;
    private F1RecyclerAdapter recyclerAdapter;

    public RecyclerFragment(JsonHomeTabsList jsonHomeTabsList) {
        // Required empty public constructor
        this.jsonHomeTabsList = jsonHomeTabsList;
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
        if (jsonHomeTabsList.getTagE().equals("today")) minPager = 1;

        mF1RRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        mF1RRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        mF1RRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        mF1RRefreshLayout.setOnRefreshListener(refreshlayout -> load(true));
        mF1RRefreshLayout.setOnLoadMoreListener(refreshlayout -> load(false));


    }

    private void load(boolean isUpData) {

        if (isUpData) page = minPager;
        else page++;

        String url = AppInfo.getUrl(jsonHomeTabsList, page);

        Tools.showToast(getContext(), url);
        Log.i("TEST_TT", url);

        new LoadUrl(activity, url, new LoadUrl.OnCall() {


            @Override
            public void error(Exception e) {

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


                if (jsonHomeF1ImageLists !=null && jsonHomeF1ImageLists.size() > 0) {

                    //添加日期
                    if (jsonHomeTabsList.getTagE().equals("today")){
                        JsonHomeF1ImageList jsonHomeF1ImageList = new JsonHomeF1ImageList();
                        jsonHomeF1ImageList.setTimeItem(true);
                        jsonHomeF1ImageList.setItemTime(getItem(page));
                        jsonHomeF1ImageLists.add(0,jsonHomeF1ImageList);
                    }


                    if (recyclerAdapter == null || isUpData) {
                        recyclerAdapter = new F1RecyclerAdapter(activity,AppInfo.APP_HOME_F1_ITEM_STYLE, jsonHomeF1ImageLists , RecyclerFragment.this);

                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(AppInfo.APP_HOME_F1_ITEM_NUMBER, StaggeredGridLayoutManager.VERTICAL);
                        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//防止Item切换
                        mF1RRecycler.setLayoutManager(layoutManager);
                        mF1RRecycler.setAdapter(recyclerAdapter);
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


    @Override
    protected void initData() {
        mF1RRefreshLayout.autoRefresh();//自动刷新
        load(true);
    }


    private void initView() {
        mF1RRefreshLayout = inflate.findViewById(R.id.m_f1_r_refreshLayout);
        mF1RRecycler = inflate.findViewById(R.id.m_f1_r_recycler);
    }






    //item 点击
    @Override
    public void onItemClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList, List<JsonHomeF1ImageList> jsonHomeF1ImageLists) {
        showToast(activity,"点击列表"+position);
        Tools.startLookImage(activity,page,position,this.jsonHomeTabsList, jsonHomeF1ImageLists);
    }

    //下载点击
    @Override
    public void onItemDownLoadClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {
        showToast(activity,"点击列表"+position+"的下载");
    }

    //收藏点击
    @Override
    public void onItemLikeClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {
        showToast(activity,"点击列表"+position+"的收藏");
    }
}