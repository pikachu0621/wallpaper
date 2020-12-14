package com.pikachu.book.tools.base;
/**
 * K V 储存
 * <p>
 * 强制横屏    记
 * 排序   记
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pikachu.book.R;


public class BaseActivity extends AppCompatActivity {


    private Toast toast;
    private OnBackEvent onBackEvent;
    private Intent intent;
    private Value value;
    private Point point;


    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




    }*/

    /**
     * toast
     */
    public Toast showToast(String msg) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }


    /**
     * 设置监听
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home || onBackEvent != null)
            onBackEvent.backEvent();
        return super.onOptionsItemSelected(item);
    }


    /**
     * 返回键实现 接口
     */
    public interface OnBackEvent {
        void backEvent();
    }


    /**
     * 设置md ActionBar 头部
     *
     * @param isShowHome  是否显示返回键
     * @param title       设置标题 null 不设置
     * @param subTitle    设置复标题 null 不设置
     * @param onBackEvent 设置 返回键的监听 null 不设置
     * @return
     */
    public ActionBar setHead(boolean isShowHome, String title,
                             String subTitle, OnBackEvent onBackEvent) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(isShowHome);
        if (title != null)
            actionBar.setTitle(title);
        if (subTitle != null)
            actionBar.setSubtitle(subTitle);
        this.onBackEvent = onBackEvent;

        return actionBar;
    }


    /**
     * 简单的  Activity 接收信息
     */
    public String getStringExtra(String name) {
        intent = getIntent();
        return intent.getStringExtra(name);
    }

    public int getIntExtra(String name, int defaultValue) {
        intent = getIntent();
        return intent.getIntExtra(name, defaultValue);
    }

    public float getFloatExtra(String name, float defaultValue) {
        intent = getIntent();
        return intent.getFloatExtra(name, defaultValue);
    }

    /**
     * 弹出加载弹框
     *
     * @param isOnTouch 触摸空白或者返回键是否关闭 dialog
     * @param msg       消息
     * @return dialog
     */
    /*
    public Dialog showLoadDialog(boolean isOnTouch, String msg) {
        Dialog dialog = new Dialog(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.ui_dialog, null);
        TextView textView = inflate.findViewById(R.id.ui_dialog_text);
        textView.setText(msg);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        Value screenSize = getScreenSize();//获取屏幕宽高
        int w = (int) (screenSize.x * 0.6f);
        if (layoutParams.width > w)
            layoutParams.width = w;
        dialog.setContentView(inflate);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(isOnTouch);
        dialog.setCancelable(isOnTouch);
        dialog.show();

        return dialog;
    }*/


    /**
     * 没原来好用，不仅建议用
     * <br/>两个 按钮的 弹窗
     *
     * @param title        标题
     * @param msg          消息
     * @param leftStr      左按键String
     * @param leftOnClick  左按键监听
     * @param rightStr     右按键String
     * @param rightOnClick 右按键监听
     * @return
     */
    @Deprecated
    public AlertDialog.Builder showDialog(String title, String msg,
                                          String leftStr, DialogInterface.OnClickListener leftOnClick,
                                          String rightStr, DialogInterface.OnClickListener rightOnClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg);
        if (leftStr != null)
            builder.setNegativeButton(leftStr, leftOnClick);
        if (rightStr != null)
            builder.setPositiveButton(rightStr, rightOnClick);
        builder.show();
        return builder;
    }


    /**
     * 没原来好用，不建议用
     * <br/>
     * 存数据
     *
     * @param key k
     * @param var v
     * @return
     */
    @Deprecated
    public SharedPreferences addKV(String name, String key, String var) {
        SharedPreferences sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, var);
        edit.apply();
        //edit.commit();
        return sharedPreferences;
    }


    public static class Value {
        public int x, y;
    }

    /**
     * 获取屏幕 宽高
     * @return
     */
    public Value getScreenSize() {
        if (point == null)
            point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        if (value == null)
            value = new Value();
        value.x = point.x;
        value.y = point.y;
        return value;
    }


    /**
     * 全屏  在setContentView() 前调用
     */
    public void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 更具男女进行主题设置
     * @param isBoy
     * @param viewGroup
     */
    public void setTheme(boolean isBoy, ViewGroup viewGroup) {
        setTheme(isBoy, false, viewGroup);
    }

    /**
     * 更具男女进行主题设置
     * @param isBoy
     */
    public void setTheme(boolean isBoy) {
        setTheme(isBoy, true, null);
    }


    private void setTheme(boolean isBoy, boolean isAppBar, ViewGroup viewGroup) {


        //更具男女进行主题设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //状态栏
            getWindow().setStatusBarColor(isBoy ? getResources().getColor(R.color.purple_700) : getResources().getColor(R.color.purge_800));

            int color = isBoy ? getResources().getColor(R.color.purple_700) : getResources().getColor(R.color.purge_800);
            if (isAppBar) {
                //AppBar
                getSupportActionBar().setBackgroundDrawable(new
                        ColorDrawable(color));
            } else {
                viewGroup.setBackgroundColor(color);
            }
        }
    }


}
