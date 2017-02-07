package com.example.tt.lazzy_weekend.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;
import com.example.tt.lazzy_weekend.view.adapter.InterstAdapter;


/**
 * Created by my on 2017/1/17.
 */

public class UserIntrestActivity extends BaseActivity{

    private GridView interestGrid;
    private InterstAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        interestGrid = (GridView) findViewById(R.id.interests_grid);
        adapter = new InterstAdapter(this);
        interestGrid.setAdapter(adapter);
    }

    public void onClickBack(View view) {
        removeActivity();
    }

    public void onClickSubmit(View view) {
        boolean isChoose = SharedPreferencesTools.getBoolean(this, "interestIsCheck");

        if (isChoose){
            //已有勾选内容,跳转到主页面,并清空栈底
            SharedPreferencesTools.putBoolean(this,"NoLogin",true);
            startActivity(new Intent(this, MainActivity.class));
            removeAllActivity();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }else {
            //没有勾选任何内容 toast
            showToast("还没有选兴趣标签哦~");
        }
    }
}
