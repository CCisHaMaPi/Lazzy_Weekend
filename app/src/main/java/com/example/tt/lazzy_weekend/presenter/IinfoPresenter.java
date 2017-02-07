package com.example.tt.lazzy_weekend.presenter;

import com.example.tt.lazzy_weekend.model.bean.AppendInfo;

import java.util.List;

/**
 * Created by my on 2017/1/18.
 */

public interface IinfoPresenter {

    void queryList(double lon, double lat, int page);

    public interface IPresenterCallback{
        public void refreshView(List<AppendInfo.ResultBean> result);
    }
}
