package com.pikachu.wallpaper.search;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.index.one.F1RecyclerAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseActivity;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import static com.pikachu.wallpaper.util.app.Tools.dp2px;
import static com.pikachu.wallpaper.util.app.Tools.getItem;

public class SearchActivity extends BaseActivity implements F1RecyclerAdapter.OnItemClickListener {

    private RelativeLayout searchRelative1;
    private View searchView;
    private Toolbar searchToolbar;
    private JsonHomeTabsList tab;
    private int minPager;
    private int page;
    private F1RecyclerAdapter recyclerAdapter;
    private SmartRefreshLayout searchRefreshLayout;
    private RecyclerView searchRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search, R.id.search_view);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        initView();
        init();

    }

    private void init() {

        tab = getSerializableExtra(AppInfo.APP_KEY_LOOK_IMAGE, JsonHomeTabsList.class);
        setSupportActionBar(searchToolbar);
        setHead(true, tab.getTabStr(), null, this::finish);

        initRefreshLayout();
        searchRefreshLayout.autoRefresh();//自动刷新
        load(true);

    }

    private void initRefreshLayout() {
        if (tab.getTagE().equals("today")) minPager = 1;
        int topHeight =  Tools.getStatusBarHeight(this) + getSupportActionBar().getHeight() + 10;
        searchRecycler.setPadding(0,dp2px(this,topHeight),0,0);
        searchRefreshLayout.setHeaderInsetStart(topHeight);
        searchRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        searchRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        searchRefreshLayout.setEnableAutoLoadMore(true);
        searchRefreshLayout.setOnRefreshListener(refreshLayout -> load(true));
        searchRefreshLayout.setOnLoadMoreListener(refreshLayout -> load(false));
    }


    private void load(boolean isUpData) {

        if (isUpData) page = minPager;
        else page++;

        String url = AppInfo.getUrl(tab, page);

        new LoadUrl(this, url, new LoadUrl.OnCall() {

            @Override
            public void error(Exception e) {

                if (isUpData)
                    searchRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
                else {
                    searchRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    page--;
                }
            }

            @Override
            public void finish(String str) {
                //加载成功后 截取需要的字符串

                List<JsonHomeF1ImageList> jsonHomeF1ImageLists = new Gson().fromJson(str, new TypeToken<List<JsonHomeF1ImageList>>() {
                }.getType());


                if (jsonHomeF1ImageLists != null && jsonHomeF1ImageLists.size() > 0) {

                    //添加日期
                    if (tab.getTagE().equals("today")) {
                        JsonHomeF1ImageList jsonHomeF1ImageList = new JsonHomeF1ImageList();
                        jsonHomeF1ImageList.setTimeItem(true);
                        jsonHomeF1ImageList.setItemTime(getItem(page));
                        jsonHomeF1ImageLists.add(0, jsonHomeF1ImageList);
                    }


                    if (recyclerAdapter == null || isUpData) {
                        recyclerAdapter = new F1RecyclerAdapter(SearchActivity.this, AppInfo.APP_SEARCH_ITEM_STYLE, jsonHomeF1ImageLists, SearchActivity.this);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(AppInfo.APP_SEARCH_ITEM_NUMBER, StaggeredGridLayoutManager.VERTICAL);
                        searchRecycler.setLayoutManager(layoutManager);
                        searchRecycler.setAdapter(recyclerAdapter);
                    } else
                        recyclerAdapter.addList(jsonHomeF1ImageLists);

                    if (isUpData) searchRefreshLayout.finishRefresh(true);
                    else searchRefreshLayout.finishLoadMore(true);

                } else {
                    if (isUpData) searchRefreshLayout.finishRefresh(true);
                    else {
                        page--;
                        searchRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }

            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    private void initView() {
        searchRelative1 = findViewById(R.id.search_relative1);
        searchView = findViewById(R.id.search_view);
        searchToolbar = findViewById(R.id.search_toolbar);

        searchRefreshLayout = findViewById(R.id.search_refreshLayout);
        searchRecycler = findViewById(R.id.search_recycler);
    }


    @Override
    public void onItemClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList, List<JsonHomeF1ImageList> jsonHomeF1ImageLists) {
        Tools.startLookImage(this, page, position, tab, jsonHomeF1ImageLists);
    }

    @Override
    public void onItemDownLoadClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {
    }

    @Override
    public void onItemLikeClick(View v, int position, JsonHomeF1ImageList jsonHomeF1ImageList) {
    }
}