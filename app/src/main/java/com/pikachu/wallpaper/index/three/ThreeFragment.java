package com.pikachu.wallpaper.index.three;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.util.base.BaseFragment;

public class ThreeFragment extends BaseFragment {


    private View inflate;
    private FragmentActivity activity;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_three, container, false);
        activity = getActivity();

        init();
        return inflate;
    }

    private void init() {

    }

    @Override
    protected void initData() {

    }
}