package com.example.tt.lazzy_weekend.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;


/**
 * Created by my on 2017/1/16.
 */

public class UserInfoActivity extends BaseActivity {

    private RadioButton rbMale, rbFemale, rbParent, rbInlove, rbSingle;
    private RadioGroup sexGroup, marriedGroup;
    private String sex, isMarried;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initView();
    }
    //初始化各个控件
    private void initView(){
        rbMale = (RadioButton) findViewById(R.id.user_male_rb);
        rbFemale = (RadioButton) findViewById(R.id.user_female_rb);
        rbParent = (RadioButton) findViewById(R.id.user_parent_rb);
        rbInlove = (RadioButton) findViewById(R.id.user_inlove_rb);
        rbSingle = (RadioButton) findViewById(R.id.user_single_rb);
        sexGroup = (RadioGroup) findViewById(R.id.sex_group);
        marriedGroup = (RadioGroup) findViewById(R.id.married_group);
        sex = SharedPreferencesTools.getString(this, "sex");
        isMarried = SharedPreferencesTools.getString(this, "isMarried");
        switch (sex){
            case "男":
                rbMale.setChecked(true);
                break;
            case "女":
                rbFemale.setChecked(true);
                break;
        }
        switch (isMarried){
            case "亲子":
                rbParent.setChecked(true);
                break;
            case "情侣":
                rbInlove.setChecked(true);
                break;
            case "单身":
                rbSingle.setChecked(true);
                break;
        }

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //保存相应的用户信息
                switch (checkedId){
                    case R.id.user_male_rb:
                        sex = "男";
                        break;
                    case R.id.user_female_rb:
                        sex = "女";
                        break;
                }
                SharedPreferencesTools.putString(UserInfoActivity.this, "sex", sex);
            }
        });
        marriedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.user_parent_rb:
                        isMarried = "亲子";
                        break;
                    case R.id.user_inlove_rb:
                        isMarried = "情侣";
                        break;
                    case R.id.user_single_rb:
                        isMarried = "单身";
                        break;
                }
                SharedPreferencesTools.putString(UserInfoActivity.this, "isMarried", isMarried);
            }
        });
    }

    public void onClickBack(View view) {
        removeActivity();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public void onClickSubmit(View view) {
        String sex = SharedPreferencesTools.getString(this, "sex");
        String isMarried = SharedPreferencesTools.getString(this, "isMarried");

        //检查勾选情况
        if(TextUtils.isEmpty(sex) || TextUtils.isEmpty(isMarried)){
            showToast("为能给您推荐更适合的产品,请选择个人状态哦!~");
            return;
        }
        startActivity(new Intent(this, UserIntrestActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
