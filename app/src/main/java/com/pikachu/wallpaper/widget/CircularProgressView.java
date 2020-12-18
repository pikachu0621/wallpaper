/**
 * pikachu
 * 2020/12/18
 * 圆形进度条
 */

package com.pikachu.wallpaper.widget;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;



public class CircularProgressView extends View {


    private Paint paint;
    private Path path;


    private float max = 2f; //总进度
    private float progress = 2f; //当前进度
    private int progressBigWidth = 8;//大圆粗细
    private int progressBigColor = 0x50FFFFFF;//大圆颜色 0x00000000
    private int progressMinWidth = 8;//小圆粗细
    private int progressMinStartColor = 0xFFFFFFFF;//小圆颜色
    //private int progressMinFinishColor = 0xFFFFFFFF;//小圆 结束颜色

    private String textTime="00.00%";
    private int textTimeColor = 0xFFFFFFFF;//字体颜色

    private int height;
    private int width;
    private int isWidth;

    private float minTopX;
    private float minTopY;

    private float minBottomX;
    private float minBottomY;
    private Context context;
    private float radianP;
    private float radianPv;


    public CircularProgressView(Context context) {
        super(context);
        this.context = context;
    }

    public CircularProgressView(Context context, @Nullable AttributeSet attrs) {
        //xml编辑调用 没来得及写xml方法
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathLx(canvas);
    }


    //根据控件大小  计算坐标
    private void init() {
        height = getHeight();
        width = getWidth();
        isWidth = Math.min(height, width);
        minTopX = (width - isWidth) / 2 + progressBigWidth / 2;
        minTopY = (height - isWidth) / 2 + progressBigWidth / 2;
        minBottomX = minTopX + isWidth - progressBigWidth;
        minBottomY = minTopY + isWidth - progressBigWidth;
    }


    @SuppressLint("NewApi")
    private void pathLx(Canvas canvas) {


        if (paint == null) {
            init();//根据控件大小  计算坐标
            paint = new Paint();//画笔
        }
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeCap(Paint.Cap.ROUND);//半圆笔帽
        paint.setStyle(Paint.Style.STROKE);//不填充

        paint.setStrokeWidth(progressBigWidth);//画笔宽度
        paint.setColor(progressBigColor);//画笔颜色
        canvas.drawCircle(width / 2, height / 2, isWidth / 2 - progressBigWidth / 2, paint);//画布画圆

        if (path != null) {
            path.close();
            path = null;
        }
        /*if (progressBigColor == 0x0){
            paint.setColor(0xffffffff);//画笔颜色
        }*/
        path = new Path();//路径
        path.addArc(minTopX, minTopY, minBottomX, minBottomY, 90, 360 / max * progress);//路径画弧
        paint.setStrokeWidth(progressMinWidth);//画笔宽度
        // 渐变效果
        /*
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, getHeight(),  progressMinStartColor , progressMinFinishColor,CLAMP);
        paint.setShader(linearGradient);
        */
        paint.setColor(progressMinStartColor);
        canvas.drawPath(path, paint);//添加路径 画笔 到画布
        paint.reset();
        paint.setStyle(Paint.Style.FILL);//填充
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(1);//画笔宽度
        paint.setColor(textTimeColor);
        paint.setTextSize(isWidth/6);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        //paint.setShader(linearGradient);

        canvas.drawText((progress/max*100)+"%",width/2,height/2+isWidth/16,paint);
    }





    //////////////////动画////////////////



    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            final float runtime = 360F * radianP;

            for (float i=0;i<=runtime;i++){
                final float ii=i;
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setProgress( ii*radianPv );
                    }
                });
                try {
                    //long l = (long) ((runtime / 360F) * 2);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            thread.interrupt();
        }
    };
    private Thread thread= new Thread(runnable);



    public void startAnimation(float progress_1){
        radianP = progress_1 / max;
        radianPv = max / 360F;
        if (thread!=null) thread.interrupt();
        thread.start();
    }




    //////////////////////////属性设置///////////////////////


    public void setMaxProgress(float max) {
        this.max = max;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setProgressBigColor(int progressBigColor) {
        this. progressBigColor = progressBigColor;
        invalidate();
    }

    public void setProgressBigWidth(int progressBigWidth) {
        this.progressBigWidth = progressBigWidth;
        invalidate();
    }

    public void setProgressMinWidth(int progressMinWidth) {
        this.progressMinWidth = progressMinWidth;
        invalidate();
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
        invalidate();
    }


    public void setTextTimeColor(int textTimeColor) {
        this.textTimeColor = textTimeColor;
        invalidate();
    }

    public void setBarColor(@ColorInt int progressMinStartColor) {
        this.progressMinStartColor = progressMinStartColor;
        invalidate();
    }



}
