package com.pikachu.wallpaper.look;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.app.Tools;
import com.pikachu.wallpaper.util.base.BaseFragment;


public class LookImageFragment extends BaseFragment {


    private final String url;
    private final View.OnClickListener onClickListener;
    private View inflate;
    private PhotoView lookFragmentImage1;
    private FragmentActivity activity;


    public LookImageFragment(String url, View.OnClickListener onClickListener) {
        this.url = Tools.hdImage(url);
        this.onClickListener = onClickListener;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_look_image, container, false);
        lookFragmentImage1 = inflate.findViewById(R.id.look_fragment_image1);
        activity = getActivity();
        return inflate;
    }

    @Override
    protected void initData() {
        lookFragmentImage1.setOnClickListener(onClickListener);

        Glide.with(activity)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into(lookFragmentImage1);



    }




}