package com.pikachu.wallpaper.index.three;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.pikachu.wallpaper.R;

public class F3SettingsView {



    private final Activity activity;
    private final View view;


    public F3SettingsView(Activity activity) {
        this.activity = activity;
        view = LinearLayout.inflate(activity, R.layout.ui_home_f3_settings, null);
        init();
    }

    private void init() {

    }


    public View getView() {
        return view;
    }

}
