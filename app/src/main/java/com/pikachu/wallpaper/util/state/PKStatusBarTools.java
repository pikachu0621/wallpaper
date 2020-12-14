/**
 * 沉浸式状态栏
 *
 * Created by PiKaChu on 2020/08/17.
 * 食用地址文档：http://note.youdao.com/noteshare?id=f1507c0cd2e1a256675ee066ecbd4562&sub=2A2787DE480C4D9499E98779257C0703
 *
 *
 *
 *
 * 更好的沉浸式工具    https://github.com/gyf-dev/ImmersionBar    介绍  https://www.jianshu.com/p/8975d7b8a9f0
 */


package com.pikachu.book.tools.state;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;

import java.lang.reflect.Method;


public class PKStatusBarTools {

    private Activity activity;

    @ColorInt
    private int COLOR=0x50000000;

    private int option =View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //布局延展至底部导航栏 （并且布局也会延展至状态栏）
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE ;//Activity全屏显示，且状态栏被覆盖掉





    private int addFlags;

    @ColorInt
    private int NONBgColor = 0x00000000,STSBgColor=0x00000000;


    public PKStatusBarTools(Activity activity) {
        this.activity = activity;
    }

    public static PKStatusBarTools with(Activity activity) {
        return new PKStatusBarTools(activity);
    }


    //隐藏状态栏 (4.4)
    public PKStatusBarTools hideSTS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addFlags =WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }else {
            addFlags= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        return this;
    }


    //布局不延展至 底部导航栏和状态栏 (不能与  hideNON()/noToBar()/hideSTS() 同用)
    public PKStatusBarTools noToBar() {
        this.option ^=  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  ;
        return this;
    }

    //布局不延展至底部导航栏 (不能与  hideNON()/noToBar()/ 同用)
    public PKStatusBarTools noToNON() {
        this.option ^=  View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION ;
        return this;
    }




    //隐藏底部导航栏 (4.4)
    public PKStatusBarTools hideNON() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.option |=View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        return this;
    }





    //设置状态栏字体颜色为黑色  android v >= 23 (6.0)
    public PKStatusBarTools setSTSTestBlack() {
        setSTSTestBlackPr(COLOR);
        return this;
    }

    //设置状态栏字体颜色为黑色  android v >= 23 (6.0)
    public PKStatusBarTools setSTSTestBlack(@ColorInt int on5xTo8xColor) {
        setSTSTestBlackPr(on5xTo8xColor);
        return this;
    }


    private void setSTSTestBlackPr(@ColorInt int on5xTo6xColor){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }else {
            setSTSBgColor(on5xTo6xColor);
        }


    }







    //设置底部导航栏字体颜色为黑色  android v >= 26 (8.0)
    public PKStatusBarTools setNONTestBlack(){
        setNONTestBlackPr(COLOR);
        return this;
    }

    //设置底部导航栏字体颜色为黑色  android v >= 26 (8.0)
    public PKStatusBarTools setNONTestBlack(@ColorInt int on5xTo8xColor){
        setNONTestBlackPr(on5xTo8xColor);
        return this;
    }


    private void setNONTestBlackPr(@ColorInt int on5xTo8xColor){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.option |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }else{
            setNONBgColor(on5xTo8xColor);
        }
    }




    public PKStatusBarTools setNONBgColor(@ColorInt int color) {
        this.NONBgColor = color;
        return this;
    }
    public PKStatusBarTools setSTSBgColor(@ColorInt int color) {
        this.STSBgColor = color;
        return this;
    }





    public void init() {
        Window window = this.activity.getWindow();
        //当前版本 >=4.4 19
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT){

            if (Build.VERSION.SDK_INT <  Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏，设置这个，如果有导航栏，底部布局会被导航栏遮住
            }
            window.getDecorView().setSystemUiVisibility(option);
            window.addFlags(addFlags);
            //>=5.0  21
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(STSBgColor);//状态栏背景颜色
                window.setNavigationBarColor(NONBgColor);//底部导航栏背景颜色
            }
        }
        if (activity instanceof PKStatusBarActivity ){
            ((PKStatusBarActivity)activity).setPkStatusBarTools(this);
        }
    }
















    /**
     * 设置底部导航栏颜色  android >= 5.0
     *
     * @param activity
     * @param color
     */
    public static void setNONBgColor(Activity activity, @ColorInt int color) {
        //>=5.0  21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
           setNONBgColor(window,color);
        }
    }

    public static void setNONBgColor(Window window, @ColorInt int color) {
        //>=5.0  21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(color);
        }
    }


    /**
     * 设置状态栏背景颜色   android >= 5.0
     *
     * @param activity
     * @param color
     */
    public static void setSTSBgColor(Activity activity, @ColorInt int color) {
        //>=5.0  21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            setSTSBgColor(window,color);
        }
    }

    public static void setSTSBgColor(Window window, @ColorInt int color) {
        //>=5.0  21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }





   /* *//**
     * 设置状态栏黑色字体图标
     *
     * @param activity
     * @return
     *//*
    public static boolean setSTSBlack(Activity activity){
        return QMUIStatusBarHelper.setStatusBarLightMode(activity);
    }

    *//**
     * 设置状态栏白色字体图标
     *
     * @param activity
     * @return
     *//*
    public static boolean setSTSWhite(Activity activity){
        return QMUIStatusBarHelper.setStatusBarDarkMode(activity);
    }

*/



    /**
     * 获取状态栏高
     *
     * @param context
     */
    public static int getSTSHeight(Context context) {
        int statusBarHeight1 = -1;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        return statusBarHeight1;
    }



    /**
     * 获取虚拟按键的高度
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }


    /**
     * 检查是否存在虚拟按键栏
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     */
    public static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Exception e) {
            }
        }
        return sNavBarOverride;
    }






}
