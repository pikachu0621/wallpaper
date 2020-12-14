/**
 * 常用静态工具
 */

package com.pikachu.book.tools.untli;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.cls.sql.F2BooksData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    private static Toast toastT;


    /**
     * toast
     */
    public static Toast showToast(Context context, String msg) {
        if (toastT != null) toastT.cancel();
        toastT = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toastT.show();
        return toastT;
    }


    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 设置一个占位view 用于占位状态栏
     *
     * @param context
     * @param view
     */
    public static void setNonHigh(Context context, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = getStatusBarHeight(context);
        view.setLayoutParams(layoutParams);
    }



    // 转化十六进制编码为字符串
    public static String toStringHex2(String s) {
        String decode="";
        try {
            decode = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }





    /**
     * 根据字符串截取原字符串中某段字符串
     * 不包含 indexStr符串和 endStr字符串
     *
     * @param content  源字符串
     * @param indexStr 开始字符串 （唯一） 可空（空为从0开始）
     * @param endStr   结束字符串（唯一）可空（空为截取到最后一个）
     * @return
     */
    public static String cutStr(String content, String indexStr, String endStr) {
        int index, end;
        if (indexStr == null || indexStr.equals(""))
            index = 0;
        else {
            index = content.indexOf(indexStr);
            if (index == -1)
                index = 0;
            else
                index += indexStr.length();
        }

        //第一次截取
        String oneStr = content.substring(index);

        if (endStr == null || endStr.equals(""))
            end = oneStr.length();
        else {
            end = oneStr.indexOf(endStr);
            if (end == -1)
                end = oneStr.length();
        }

        //第二次截取
        return oneStr.substring(0, end);
    }


    /**
     * dp  转  px
     *
     * @param dpValue px值
     * @param context 上下文
     */
    public static int dp2px(Context context, int dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics()) + 0.5F);
    }


    /**
     * 判断是否晚上
     * @return
     */
    public static boolean isNight() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 6)
            return true;
        if (a > 18 && a <= 24)
            return true;
        return false;

    }













    /**
     * 获取屏幕最大亮度
     * @return
     */
    public static int getBrightnessMax() {
        try {
            Resources system = Resources.getSystem();
            int resId = system.getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
            if (resId != 0) {
                return system.getInteger(resId);
            }
        } catch (Exception ignore) {
        }
        return 255;
    }




    /**
     * 获取屏幕的亮度
     */
    public static int getScreenBrightness(Context context) {

        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;

    }


    /**
     * 保存当前的屏幕亮度值，并使之生效
     */
    public static void setScreenBrightness(Activity activity, int paramInt) {
        Window localWindow = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.screenBrightness = paramInt / (float)getBrightnessMax() ;
        localWindow.setAttributes(localLayoutParams);
    }


    /**
     * 设置跳转浏览器
     * @param activity
     * @param url
     */
    public static void jumpURl(Activity activity,String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }



}
