package com.example.tt.lazzy_weekend.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;


/**
 * Created by my on 2017/1/13.
 */

public class SplashActivity extends BaseActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = (ImageView) findViewById(R.id.splash_image);

        //创建补间动画
        final Animation animation = new AlphaAnimation(0,1);
        animation.setDuration(3000);
        animation.setFillAfter(true);

        //监听动画,动画结束时结束开场页面
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时设置图片
                image.setImageResource(R.mipmap.splash);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时跳转页面.
                //查询是否已登录;
                Boolean noLogin = SharedPreferencesTools.getBoolean(SplashActivity.this, "NoLogin");
                //安装后第一次开始APP时,会返回默认false.则跳转到登录页面
                if (!noLogin){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else {
                    //已登录,直接跳转到主页面
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                removeActivity();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //给activity设置开场动画
        image.startAnimation(animation);
    }
}
