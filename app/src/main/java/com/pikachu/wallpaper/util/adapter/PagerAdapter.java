package com.pikachu.book.tools.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> fragments;
    private List<String> tab;

    public PagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        this(fm,fragments,null);
    }
    public PagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, List<String> tab) {
        super(fm);
        this.fragments = fragments;
        this.tab = tab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (tab != null)
            return tab.get(position);
        return super.getPageTitle(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { }
}
