
package com.pikachu.wallpaper.other.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.util.app.Tools;


public class SettingsFragment extends Fragment {


    private final int barHeight;
    private View inflate;
    private LinearLayout fragmentSettingLin1;
    private LinearLayout fragmentSettingLin2;
    private LinearLayout fragmentSettingLin3;
    private FragmentActivity activity;

    public SettingsFragment(int barHeight) {
        // Required empty public constructor
        this.barHeight = barHeight;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_settings, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {



        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fragmentSettingLin3.getLayoutParams();
        layoutParams.topMargin = Tools.dp2px(activity,Tools.getStatusBarHeight(activity) + barHeight + 10);
        fragmentSettingLin3.setLayoutParams(layoutParams);


        fragmentSettingLin1.setOnClickListener(v -> {
            new AlertDialog.Builder(activity)
                    .setTitle("欢迎使用!")
                    .setMessage("1.本应用为图片阅览器\n" +
                            "2.APP内的图片均归网友上传。\n" +
                            "3.项目只用于学习交流，毕业设计，已开源。APP禁止二次打包后用于任何商业用途，一切后果自负。\n" +
                            "4.图片公共版权，个人和商业使用全部免费。\n" +
                            "by：Pikachu_WeChat")
                    .setPositiveButton("确定", null)
                    .show();

        });
        fragmentSettingLin2.setOnClickListener(v -> Tools.jumpURl(activity, "https://github.com/2825436553/wallpaper"));

    }





    private void initView() {
        fragmentSettingLin1 = inflate.findViewById(R.id.fragment_setting_lin1);
        fragmentSettingLin2 = inflate.findViewById(R.id.fragment_setting_lin2);
        fragmentSettingLin3 = inflate.findViewById(R.id.fragment_setting_lin3);
    }
}