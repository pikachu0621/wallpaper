package com.pikachu.wallpaper.index.three;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.pikachu.wallpaper.R;

public class F3SettingsView {


    private final Activity activity;
    private final View view;
    private final OnSettingsClickListener onSettingsClickListener;
    private LinearLayout mF3SettingsLin1;
    private LinearLayout mF3SettingsLin2;
    private LinearLayout mF3SettingsLin3;
    private LinearLayout mF3SettingsLin4;


    public interface OnSettingsClickListener{
        void onKeepClick(View view);
        void onDownloadClick(View view);
        void onHistoryClick(View view);
        void onSettingClick(View view);
    }


    public F3SettingsView(Activity activity,OnSettingsClickListener onSettingsClickListener) {
        this.activity = activity;
        this.onSettingsClickListener = onSettingsClickListener;
        view = LinearLayout.inflate(activity, R.layout.ui_home_f3_settings, null);
        initView();
        init();
    }

    private void init() {
        mF3SettingsLin1.setOnClickListener(onSettingsClickListener::onKeepClick);
        mF3SettingsLin2.setOnClickListener(onSettingsClickListener::onDownloadClick);
        mF3SettingsLin3.setOnClickListener(onSettingsClickListener::onHistoryClick);
        mF3SettingsLin4.setOnClickListener(onSettingsClickListener::onSettingClick);
    }


    public View getView() {
        return view;
    }

    private void initView() {
        mF3SettingsLin1 = view.findViewById(R.id.m_f3_settings_lin1);
        mF3SettingsLin2 = view.findViewById(R.id.m_f3_settings_lin2);
        mF3SettingsLin3 = view.findViewById(R.id.m_f3_settings_lin3);
        mF3SettingsLin4 = view.findViewById(R.id.m_f3_settings_lin4);
    }
}
