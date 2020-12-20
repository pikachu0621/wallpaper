package com.pikachu.wallpaper.other;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.other.fragment.SettingsFragment;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.base.BaseActivity;
import com.pikachu.wallpaper.util.state.QMUIStatusBarHelper;

public class OtherActivity extends BaseActivity {


    private Toolbar otherToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other,R.id.other_view);
        initView();
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setSupportActionBar(otherToolbar);
        setHead(true,getStringExtra(AppInfo.APP_KEY_OTHER_STR),null,this::finish);

        //fragment 加载
        switch (getIntExtra(AppInfo.APP_KEY_LOOK_IMAGE, 0)) {

            case 0:
                //设置
                setFragment(new SettingsFragment(getBarHeight()));
                break;
            case 1:
                //历史
                break;
            case 2:
                //下载
                break;
            case 3:
                //收藏
                break;
        }

    }


    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.other, fragment).commit();
    }

    private int getBarHeight(){
        return getSupportActionBar().getHeight();
    }


    private void initView() {
        otherToolbar = findViewById(R.id.other_toolbar);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

}