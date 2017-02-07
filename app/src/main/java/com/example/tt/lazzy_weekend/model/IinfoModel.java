package com.example.tt.lazzy_weekend.model;

import com.example.tt.lazzy_weekend.model.bean.AppendInfo;

/**
 * Created by my on 2017/1/18.
 */

public interface IinfoModel {

    void queryData(double lon, double lat, int page);

    /**
     * Model和Presenter间进行数据的传递方法
     */
    public interface IModelCallback{
        public void setResult(AppendInfo info);
    }
}
