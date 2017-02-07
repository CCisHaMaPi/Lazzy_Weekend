package com.example.tt.lazzy_weekend.view.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.tt.lazzy_weekend.MyApplication;


/**
 * Created by my on 2017/1/16.
 */

public class BaseActivity extends AppCompatActivity {

    private MyApplication application;
    private BaseActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (application == null){
            //获取MyApplication对象
            application = (MyApplication) getApplication();
        }

        //当前上下文对象赋给BaseActivity
        mContext = this;
        //每个activity创建时自动调用
        addActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当每个activity销毁时,自动调用
        removeActivity();
    }

    /**
     * 添加Activity
     */
    public void addActivity() {
        application.addActivity(mContext);
    }

    /**
     * 销毁Activity
     */
    public void removeActivity(){
        application.removeActivity(mContext);
    }

    /**
     * 销毁所有Activity
     */
    public void removeAllActivity(){
        application.removeAllActivity();
    }

    //Toast方法,其他activity可以反复重用
    public void showToast(String text){
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
