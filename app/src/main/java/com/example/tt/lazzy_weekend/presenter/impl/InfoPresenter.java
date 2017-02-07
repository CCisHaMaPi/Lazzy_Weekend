package com.example.tt.lazzy_weekend.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.example.tt.lazzy_weekend.model.IinfoModel;
import com.example.tt.lazzy_weekend.model.bean.AppendInfo;
import com.example.tt.lazzy_weekend.model.impl.InfoModel;
import com.example.tt.lazzy_weekend.presenter.IinfoPresenter;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;

import java.util.List;

/**
 * Created by my on 2017/1/18.
 */

public class InfoPresenter implements IinfoPresenter,IinfoModel.IModelCallback {

    private String userTag;
    private InfoModel model;
    private IPresenterCallback presenterCallback;

    public InfoPresenter(IPresenterCallback presenterCallback, Context context){
        model = new InfoModel(this);
        this.presenterCallback = presenterCallback;
        userTag = SharedPreferencesTools.getString(context, "isMarried");
    }

    @Override
    public void queryList(double lon, double lat, int page) {
        model.queryData(lon, lat, page);
    }

    @Override
    public void setResult(AppendInfo info) {
        List<AppendInfo.ResultBean> result = info.getResult();
        Log.i("+++",result.toString());
//        //遍历所获得的集合
//        for (AppendInfo.ResultBean bean : result){
//            List<String> tags = bean.getTags();
//            if (!tags.contains(userTag)){
//                //如果标签中不支持前用户婚姻状态,则剔除该对象
//                result.remove(bean);
//            }
//        }
        presenterCallback.refreshView(result);
    }
}
