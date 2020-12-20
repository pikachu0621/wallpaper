/**
 * 图片查看器
 * 支持加载后一页
 * 支持双指放大，缩小，单指移动，切换图片
 * pikachu
 */

package com.pikachu.wallpaper.look;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.wallpaper.R;
import com.pikachu.wallpaper.cls.item.LookData;
import com.pikachu.wallpaper.cls.json.JsonHomeF1ImageList;
import com.pikachu.wallpaper.cls.json.JsonHomeTabsList;
import com.pikachu.wallpaper.util.adapter.PagerAdapter;
import com.pikachu.wallpaper.util.app.AppInfo;
import com.pikachu.wallpaper.util.base.BaseActivity;
import com.pikachu.wallpaper.util.gaussian.Gaussian;
import com.pikachu.wallpaper.util.state.PKStatusBarTools;
import com.pikachu.wallpaper.util.url.LoadUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LookImageActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar lookToolbar;
    private int pointer;
    private int page;
    private JsonHomeTabsList imageTab;
    private List<JsonHomeF1ImageList> imageDataList;
    private ViewPager lookPager;
    private LinearLayout lookLin1;
    private ImageView lookKeep;
    private ImageView lookDownload;
    private TextView lookText1;
    private RelativeLayout lookRelative1;
    private PagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments;
    private int currentDataMaxSize;
    private int minPager;
    private ImageView lookImage1;
    private Bitmap bitmapLod;
    private boolean isLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_image, R.id.look_view);
        initView();
        init();
    }



    private void init() {
        setSupportActionBar(lookToolbar);

        LookData serializableExtra = getSerializableExtra(AppInfo.APP_KEY_LOOK_IMAGE, LookData.class);
        assert serializableExtra != null;

        pointer = serializableExtra.getPointer();
        page = serializableExtra.getPage();
        imageTab = serializableExtra.getImageTab();
        if (imageTab != null) minPager = imageTab.getTagE().equals("today") ? 1 : page;
        else minPager = page;
        List<JsonHomeF1ImageList> imageData = serializableExtra.getImageDataList();

        imageDataList = new ArrayList<>();
        fragments = new ArrayList<>();
        for (JsonHomeF1ImageList jsonHomeF1ImageList : imageData) {
            if (jsonHomeF1ImageList.isTimeItem()) {
                pointer--;
                continue;
            }
            imageDataList.add(jsonHomeF1ImageList);
            fragments.add(new LookImageFragment(jsonHomeF1ImageList.getRaw(), LookImageActivity.this));
        }


        setHead(true, getImageTitle(pointer), null, this::finish);
        currentDataMaxSize = imageDataList.size();

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        lookPager.setAdapter(pagerAdapter);
        lookPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setCurrentImageData(getImageTitle(position), false, false);

                if (position == currentDataMaxSize - 1)
                    addImageData();
                loadGaussianBg(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        lookPager.setCurrentItem(pointer);
        //如果点击的是第一张图片 则调用一次高斯模糊
        if (pointer == 0)
            loadGaussianBg(pointer);


        lookText1.setOnClickListener(v -> setWallpaper());

    }

    //加载高斯背景
    private void loadGaussianBg(int position) {

        if (!AppInfo.APP_LOOK_GSN) return;
        Glide.with(this)
                .asBitmap()
                .load(imageDataList.get(position).getSmallUrl())
                .override(320)
                .into(new SimpleTarget<Bitmap>() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        //动画 图片置换效果
                        Drawable[] drawables = new Drawable[]{
                                bitmapLod == null ? getResources().getDrawable(android.R.color.transparent) : new BitmapDrawable(bitmapLod),
                                new BitmapDrawable((bitmapLod = Gaussian.doBlur(resource, AppInfo.APP_LOOK_GSN_R)))
                        };
                        TransitionDrawable td = new TransitionDrawable(drawables);
                        lookImage1.setImageDrawable(td);
                        td.setCrossFadeEnabled(true);
                        td.startTransition(AppInfo.APP_ANIMATION_TIME);
                    }
                });
    }

    private void initView() {

        lookToolbar = findViewById(R.id.look_toolbar);
        //lookImage = findViewById(R.id.look_image);
        lookPager = findViewById(R.id.look_pager);
        lookLin1 = findViewById(R.id.look_lin1);
        lookKeep = findViewById(R.id.look_keep);
        lookDownload = findViewById(R.id.look_download);
        lookText1 = findViewById(R.id.look_text1);
        lookRelative1 = findViewById(R.id.look_relative1);
        lookImage1 = findViewById(R.id.look_image1);
    }


    //设置壁纸
    private void setWallpaper() {

        int currentItem = lookPager.getCurrentItem();
        Glide.with(this)
                .asBitmap()
                .load(imageDataList.get(currentItem).getRaw())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            setWallpaper(resource);
                            showToast("设置完成");
                        } catch (IOException e) {
                            e.printStackTrace();
                            showToast("设置失败");
                        }
                    }
                });

        /*Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("mimeType", "image/*");
        Uri uri = Uri.parse(MediaStore.Images.Media
                .insertImage(this.getContentResolver(), bitmap, null, null));
        intent.setData(uri);
        startActivityForResult(intent, *//*SET_WALLPAPER*//*1);*/
    }


    //滑动到低加载更多 左右加
    private void addImageData() {
        if (page == -1 || imageTab == null || isLoad) return;

        page++;

        showToast("开始加载下一页");
        isLoad = true;
        new LoadUrl(this, AppInfo.getUrl(imageTab, page), new LoadUrl.OnCall() {
            ///加载失败
            @Override
            public void error(Exception e) {
                showToast("下一页加载失败\n"+e.getMessage());
                if (page > minPager) page--;
                isLoad = false;
            }

            ///加载成功
            @Override
            public void finish(String str) {
                List<JsonHomeF1ImageList> imageData = new Gson().fromJson(str, new TypeToken<List<JsonHomeF1ImageList>>() {
                }.getType());
                imageDataList.addAll(imageData);

                for (JsonHomeF1ImageList list : imageData)
                    fragments.add(new LookImageFragment(list.getRaw(), LookImageActivity.this));

                currentDataMaxSize += imageData.size();
                pagerAdapter.notifyDataSetChanged();
                showToast("加载完成");
                isLoad = false;
            }
        });

    }


    //设置数据
    private void setCurrentImageData(String title, boolean isKeep, boolean isDownload) {
        getSupportActionBar().setTitle(title);
    }

    private String getImageTitle(int position) {
        String description = imageDataList.get(position).getInfo().getDescription();
        return description == null || description.equals("") ? AppInfo.APP_AUTHOR_NAME : description;
    }

    //设置动画
    private void setAnimator(View topView, View bottomView, boolean isShow) {
        float y = 0f, y1 = 1f;
        float ty2 = -100f, ty3 = 0f;
        float ty = 100f, ty1 = 0f;
        if (!isShow) {
            y = 1f;
            y1 = 0f;
            ty = 0f;
            ty1 = 100f;
            ty2 = 0f;
            ty3 = -100f;
        }

        PropertyValuesHolder alphaProper1 = PropertyValuesHolder.ofFloat("alpha", y, y1);
        PropertyValuesHolder scaleYProper1 = PropertyValuesHolder.ofFloat("translationY", ty2, ty3);
        ValueAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(topView, alphaProper1, scaleYProper1);
        animator1.setDuration(200);

        PropertyValuesHolder alphaProper = PropertyValuesHolder.ofFloat("alpha", y, y1);
        PropertyValuesHolder scaleYProper = PropertyValuesHolder.ofFloat("translationY", ty, ty1);
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(bottomView, alphaProper, scaleYProper);
        animator.setDuration(200);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isShow) {
                    /*v3.setVisibility(View.VISIBLE);*/
                    bottomView.setVisibility(View.VISIBLE);
                    topView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (!isShow) {
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                    /*v3.setVisibility(View.GONE);*/
                }
            }
        });
        animator.start();
        animator1.start();
    }


    @Override
    public void onClick(View v) {
        setAnimator(lookRelative1, lookLin1, lookRelative1.getVisibility() == View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_look_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        //设置桌面壁纸
        if (item.getItemId() == R.id.menu_1) {

            return true;
        }

        //锁屏壁纸
        if (item.getItemId() == R.id.menu_2) {
            return true;
        }

        //设置全部
        if (item.getItemId() == R.id.menu_2) {
            return true;
        }


        //分享
        if (item.getItemId() == R.id.menu_4) {
            showToast("可以保存后分享");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}