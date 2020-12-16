package com.pikachu.wallpaper.index.tow;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.github.mmin18.widget.RealtimeBlurView;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.item.F2ItemData;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseFragment;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;
import com.pikachu.wallpaper.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.pikachu.wallpaper.util.app.Tools.showToast;
import static com.pikachu.wallpaper.util.app.Tools.strToTabsObject;


public class TowFragment extends BaseFragment implements F2RecyclerAdapter.OnItemClickListener, F2BarView.onBarImageClick {


    private View inflate;
    private FragmentActivity activity;
    private View mF2View;

    private TextView mF2Text1;
    private SmartRefreshLayout mF1RRefreshLayout;
    private RecyclerView mF1RRecycler;
    private RealtimeBlurView mF2RealtimeBlurView;
    private LinearLayout mF2Lin1;
    private ImageView mF2Image1;
    private F2RecyclerAdapter f2RecyclerAdapter;
    private F2BarView f2BarView;
    private View viewTitle;
    private StaggeredGridLayoutManager layoutManager;

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
        mF2Text1.setText("全部分类");

        mF1RRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        mF1RRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        mF1RRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        mF1RRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        //mF1RRefreshLayout.setEnablePureScrollMode(true);//是否启用纯滚动模式
        mF1RRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mF1RRefreshLayout.setOnRefreshListener(refreshlayout -> load());
        setRecyclerSlo();
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

                //数据整理
                ArrayList<F2ItemData> f2ItemData = new ArrayList<>();
                //数据整理

                JsonHomeTabsList jsonHomeTabsList = new JsonHomeTabsList();
                jsonHomeTabsList.setTabStr("热门分类");
                f2ItemData.add(new F2ItemData(jsonHomeTabsList,F2ItemData.TEXT,F2ItemData.TEXT_MAX));

                for (int i =0 ; i < jsonHomeTabsLists.size() ; i++){
                    if (i == AppInfo.APP_HOME_F2_RM ){
                        JsonHomeTabsList jsonHomeTabsList1 = new JsonHomeTabsList();
                        jsonHomeTabsList1.setTabStr("其他分类");
                        f2ItemData.add(new F2ItemData(jsonHomeTabsList1,F2ItemData.TEXT,F2ItemData.TEXT_MAX));
                    }
                    f2ItemData.add(new F2ItemData(jsonHomeTabsLists.get(i),F2ItemData.IMAGE,1));

                }


                f2RecyclerAdapter = new F2RecyclerAdapter(activity, f2ItemData, TowFragment.this);
                final GridLayoutManager manager = new GridLayoutManager(activity, AppInfo.APP_HOME_F2_ITEM_NUMBER);
                mF1RRecycler.setLayoutManager(manager);
                f2RecyclerAdapter.setGridSpanSizeLookup((gridLayoutManager, viewType, position) -> f2ItemData.get(position).getSpanSize());
                mF1RRecycler.setAdapter(f2RecyclerAdapter);



                /*layoutManager = new StaggeredGridLayoutManager(AppInfo.APP_HOME_F2_ITEM_NUMBER, StaggeredGridLayoutManager.VERTICAL);
                mF1RRecycler.setLayoutManager(layoutManager*//*new GridLayoutManager(activity, AppInfo.APP_HOME_F2_ITEM_NUMBER)*//*);*/

                f2BarView = new F2BarView(activity, TowFragment.this);
                f2RecyclerAdapter.addHeaderView(f2BarView.getView(),0);


                mF1RRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
            }
        });


    }



    //re 滑动监听
    private void setRecyclerSlo() {

        mF1RRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int i = recyclerView.computeVerticalScrollOffset();
                if (i <= 255) {
                    Log.d(AppInfo.APP_LOG, "scroll " + dx + " " + dy + " totalDy " + i);
                    mF2RealtimeBlurView.setAlpha(i);
                }
                if (i==0){
                    mF2RealtimeBlurView.setAlpha(0);
                }
            }
        });
    }





    private View setTitle(String text){
        /*if (viewTitle == null)*/
            viewTitle = LinearLayout.inflate(activity, R.layout.ui_text, null);
        ((TextView)viewTitle.findViewById(R.id.ui_text)).setText(text);
        return viewTitle;
    }



    @Override
    protected void initData() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
        mF1RRefreshLayout.autoRefresh();//自动刷新
        load();
    }

    private void initView() {
        mF2View = inflate.findViewById(R.id.m_f2_view);
        mF2Text1 = inflate.findViewById(R.id.m_f2_text1);
        mF1RRefreshLayout = inflate.findViewById(R.id.m_f1_r_refreshLayout);
        mF1RRecycler = inflate.findViewById(R.id.m_f1_r_recycler);
        mF2RealtimeBlurView = inflate.findViewById(R.id.m_f2_RealtimeBlurView);
        mF2Lin1 = inflate.findViewById(R.id.m_f2_lin1);
        mF2Image1 = inflate.findViewById(R.id.m_f2_image1);
    }

    @Override
    protected void onDisplay() {
        QMUIStatusBarHelper.setStatusBarLightMode(activity);
    }


    //点击事件
    @Override
    public void onItemClick(View v, int position, JsonHomeTabsList jsonHomeTabsList) {

    }


    //top图片点击
    @Override
    public void onClick() {

    }
}