package com.example.tt.lazzy_weekend.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by my on 2017/1/13.
 */

public class MyPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_ALPHA = 0.0f;    //最小透明度

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();    //得到view宽

        if (position < -1) {
            //出了左边屏幕时
            view.setAlpha(0);
        } else if (position <= 1) {
            if (position < 0) {
                //消失的页面
                view.setTranslationX(-pageWidth * position);  //阻止消失页面的滑动
            } else {
                //出现的页面
                view.setTranslationX(pageWidth);        //直接设置出现的页面到底
                view.setTranslationX(-pageWidth * position);  //阻止出现页面的滑动
            }
            //透明度变化
            float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position));
            view.setAlpha(alphaFactor);

        } else {
            //出了右边屏幕时
            view.setAlpha(0);
        }
    }



    int nowPostion = 0; //当前页面
    Context context;
    ArrayList<ImageView> images;

    public void setCurrentItem(Context context, int nowPostion, ArrayList<ImageView> images) {
        this.nowPostion = nowPostion;
        this.context = context;
        this.images = images;
    }

    public void setCurrentItem(int nowPostion) {
        this.nowPostion = nowPostion;
    }
}
