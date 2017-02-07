package com.example.tt.lazzy_weekend.model.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.example.tt.lazzy_weekend.model.IinfoModel;
import com.example.tt.lazzy_weekend.model.bean.AppendInfo;
import com.example.tt.lazzy_weekend.utils.JSONLoader;

/**
 * Created by my on 2017/1/18.
 */

public class InfoModel implements IinfoModel {

    public static final String INFO_URL_HEAD =
            "http://api.lanrenzhoumo.com/main/recommend/index?v=3&session_id=000040a3fb7d64ce1737c6c7bb3c7e4e157c91";

    private IModelCallback modelCallback;
    private JSONLoader jsonLoader;

    public InfoModel(IModelCallback modelCallback){
        this.modelCallback = modelCallback;

    }

    @Override
    public void queryData(double lon, double lat, int page) {
        String url = INFO_URL_HEAD+"&lon="+lon+"&page="+page+"&lat="+lat;
        Log.i("+++",url);
        final Gson gson = new Gson();
        jsonLoader = new JSONLoader();
        jsonLoader.loadJson(url, new JSONLoader.OnJSONLoaderListener() {
            @Override
            public void onJsonLoad(String json) {
                AppendInfo info = gson.fromJson(json,AppendInfo.class);
                modelCallback.setResult(info);
            }
        });
    }
}
