package com.example.tt.lazzy_weekend.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.model.bean.AppendInfo;
import com.example.tt.lazzy_weekend.presenter.IinfoPresenter;
import com.example.tt.lazzy_weekend.presenter.impl.InfoPresenter;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;
import com.example.tt.lazzy_weekend.view.adapter.InfoAdapter;

import java.util.List;

/**
 * Created by my on 2017/1/18.
 */

public class InfoFragment extends BaseFragment implements IinfoPresenter.IPresenterCallback{

    private ListView listView;
    private InfoAdapter adapter;
    private LocationClient locationClient;
    private IinfoPresenter infoPresenter;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        initView(view);
        initData();
        return view;
    }

    //初始化
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.info_list);
        infoPresenter = new InfoPresenter(this, getContext());
        locationClient = new LocationClient(getContext());
    }

    private void initData(){

        //获取当前系统时间以及上次登录时间，超过一个小时则重新定位
        long thisTime = System.currentTimeMillis();
        long lastTime = SharedPreferencesTools.getLong(getContext(), "lastTime");
        if (thisTime - lastTime > 3600*1000){
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true);
            option.setCoorType("bd09ll");

            locationClient.setLocOption(option);
            locationClient.start();

            locationClient.registerLocationListener(new BDLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    //定位的经纬度传入presenter层，并保存
                    double lon = bdLocation.getLongitude();
                    double lat = bdLocation.getLatitude();
                    infoPresenter.queryList(lon, lat, page);
                    SharedPreferencesTools.putString(getContext(), "lon", ""+lon);
                    SharedPreferencesTools.putString(getContext(), "lat", ""+lat);
                }
            });
        }else {
            //距离上次登录未满一个小时，复用经纬度
            double lon = Double.parseDouble(SharedPreferencesTools.getString(getContext(), "lon"));
            double lat = Double.parseDouble(SharedPreferencesTools.getString(getContext(), "lat"));
            infoPresenter.queryList(lon, lat, page);
        }
        //将本次登录时间保存
        SharedPreferencesTools.putLong(getContext(), "lastTime", thisTime);
    }

    //获取解析结果
    @Override
    public void refreshView(List<AppendInfo.ResultBean> result) {
        adapter = new InfoAdapter(getContext(), result);
        listView.setAdapter(adapter);
    }
}
