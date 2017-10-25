package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RegisterSelectAddressBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/8.
 */

public class GetCityDataListHttp extends BaseHttp2 {
    public GetCityDataListHttp(String provincenum ,HttpCallBackListener<RegisterSelectAddressBean> listener) {
        this.listener=listener;
        put("provincenum",provincenum);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().getCityDataList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RegisterSelectAddressBean.class;
    }
}
