package com.example.tt.lazzy_weekend.view.activitys;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;
import com.example.tt.lazzy_weekend.view.fragments.HelpFragment;
import com.example.tt.lazzy_weekend.view.fragments.InfoFragment;
import com.example.tt.lazzy_weekend.view.fragments.MineFragment;
import com.example.tt.lazzy_weekend.view.fragments.SearchFragment;


public class MainActivity extends BaseActivity {

    private Fragment infoFrag, searchFrag, helpFrag, mineFrag, defFrag;
    private RadioGroup menuGroup;
    private RadioButton radioButton;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initVeiw();
    }

    private void initFragment() {
        infoFrag = new InfoFragment();
        searchFrag = new SearchFragment();
        helpFrag = new HelpFragment();
        mineFrag = new MineFragment();

        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frag_main, infoFrag)
                .commit();
        //将默认frag设置为第一页面
        defFrag = infoFrag;
    }

    private void initVeiw() {
        menuGroup = (RadioGroup) findViewById(R.id.menu_group);
        radioButton = (RadioButton) findViewById(R.id.menu_info);
        radioButton.setChecked(true);
        //单选按钮点击切换Fragment
        menuGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.menu_info:
                        switchFragment(infoFrag);
                        break;
                    case R.id.menu_search:
                        switchFragment(searchFrag);
                        break;
                    case R.id.menu_help:
                        switchFragment(helpFrag);
                        break;
                    case R.id.menu_mine:
                        switchFragment(mineFrag);
                        break;
                }
            }
        });
    }

    public void switchFragment(Fragment newFrag) {
        if (newFrag != defFrag) {
            //判断新的Fragment是否已添加
            if (newFrag.isAdded()) {
                //已经添加，隐藏原来的Fragment，显示新的Fragment
                manager.beginTransaction()
                        .hide(defFrag)
                        .show(newFrag)
                        .commit();
            } else {
                //没有添加，隐藏原来的Fragment，添加并且显示新的Fragment
                manager.beginTransaction()
                        .hide(defFrag)
                        .add(R.id.frag_main, newFrag)
                        .commit();
            }
            //设置新的Fragment为当前的默认Frag
            defFrag = newFrag;
        }
    }

    public void onClickText(View view) {
        SharedPreferencesTools.putBoolean(this, "NoLogin", false);
    }
}
