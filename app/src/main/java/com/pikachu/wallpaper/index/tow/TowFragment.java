package com.pikachu.wallpaper.index.tow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.util.base.BaseFragment;


public class TowFragment extends BaseFragment {


    private View inflate;
    private FragmentActivity activity;

    public TowFragment() {
        // Required empty public constructor
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_tow, container, false);
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