package com.example.tt.lazzy_weekend;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.example.tt.lazzy_weekend.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by my on 2017/1/3.
 */

public class MyApplication extends Application{

    private List<AppCompatActivity> mList;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtils.initCache(getApplicationContext());
        mList = new ArrayList<>();
    }

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(AppCompatActivity activity){
        //判断要添加的对象是否已经在集合中
        if (!mList.contains(activity)){
            mList.add(activity);
        }
    }

    /**
     * 销毁单个activity
     * @param activity
     */
    public void removeActivity(AppCompatActivity activity){
        //判断要销毁的对象是否在集合中
        if (mList.contains(activity)){
            //如果存在,则销毁它,并从集合中移除
            mList.remove(activity);
            activity.finish();
        }
    }

    /**
     * 销毁所有activity
     */
    public void removeAllActivity(){
        //循环遍历集合,逐个移除并销毁
        for (AppCompatActivity activity : mList){
            activity.finish();
        }
        mList.clear();
    }
}
