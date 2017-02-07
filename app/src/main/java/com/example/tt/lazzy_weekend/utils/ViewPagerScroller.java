package com.example.tt.lazzy_weekend.utils;

/**
 * Created by my on 2017/1/17.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * 原生系统的viewpager默认页面切换速度太快,无法清晰的表现出切换的动画效果
 * 所以自定义一个滚动器,手动设置viewpager的切换速度
 */
public class ViewPagerScroller extends Scroller {

    private int mScrollDuration = 2000; // 改变默认的滑动速度

    /**
     * 手动设置切换速度方法
     *
     * @param duration
     */
    public void setScrollDuration(int duration) {
        this.mScrollDuration = duration;
    }

    public ViewPagerScroller(Context context) {
        super(context);
    }

    //在startScroll方法中将速度添加进去
    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    public void initViewPagerScroll(ViewPager viewPager) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
