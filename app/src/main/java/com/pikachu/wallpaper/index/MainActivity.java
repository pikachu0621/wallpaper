package com.pikachu.wallpaper.index;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.index.one.OneFragment;
import com.pikachu.wallpaper.index.three.ThreeFragment;
import com.pikachu.wallpaper.index.tow.TowFragment;
import com.pikachu.wallpaper.util.adapter.PagerAdapter;
import com.pikachu.wallpaper.util.base.BaseActivity;
import com.pikachu.wallpaper.util.state.PKStatusBarTools;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager mainPager;
    private ArrayList<Fragment> fragments;
    private PagerAdapter pagerAdapter;
    private BottomIdRes[] bottomIdRes;
    private int colorPurple;


    public static class BottomIdRes{
        int oldImageIdRes, newImageIdRes;
        LinearLayout viewLin;
        TextView textView;
        ImageView imageView;

        public BottomIdRes(Activity activity, @DrawableRes int oldImageIdRes, @DrawableRes int newImageRes,
                           @IdRes int linViewIdRes,@IdRes int textViewIdRes,@IdRes int imageViewIdRes) {
            this.oldImageIdRes = oldImageIdRes;
            this.newImageIdRes = newImageRes;
            this.viewLin = activity.findViewById(linViewIdRes);
            this.textView = activity.findViewById(textViewIdRes);
            this.imageView = activity.findViewById(imageViewIdRes);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }


    private void init() {
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TowFragment());
        fragments.add(new ThreeFragment());
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        mainPager.setAdapter(pagerAdapter);
        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int positionOld = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                BottomIdRes bottomIdRe = bottomIdRes[positionOld];
                bottomIdRe.textView.setVisibility(View.GONE);
                bottomIdRe.imageView.setImageResource( bottomIdRe.oldImageIdRes);
                bottomIdRe = bottomIdRes[position];
                bottomIdRe.textView.setVisibility(View.VISIBLE);
                bottomIdRe.imageView.setImageResource(bottomIdRe.newImageIdRes);
                positionOld = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }


    private void initView() {
        mainPager = findViewById(R.id.main_pager);
        colorPurple = getResources().getColor(R.color.purple);
        bottomIdRes = new BottomIdRes[]{
                new BottomIdRes(this,R.drawable.ic_home_f1,R.drawable.ic_home_f1s,R.id.main_nar_one,R.id.main_nar_text1,R.id.main_nar_image1),
                new BottomIdRes(this,R.drawable.ic_home_f2,R.drawable.ic_home_f2s,R.id.main_nar_tow,R.id.main_nar_text2,R.id.main_nar_image2),
                new BottomIdRes(this,R.drawable.ic_home_f3,R.drawable.ic_home_f3s,R.id.main_nar_three,R.id.main_nar_text3,R.id.main_nar_image3)
        };
        for (int i = 0;i<bottomIdRes.length;i++) {
            int finalI = i;
            bottomIdRes[i].viewLin.setOnClickListener(v -> mainPager.setCurrentItem(finalI));
        }


    }
}