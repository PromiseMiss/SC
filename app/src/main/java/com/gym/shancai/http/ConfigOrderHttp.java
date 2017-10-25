package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ConfigOrderBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/14.
 */

public class ConfigOrderHttp extends BaseHttp2{

    public ConfigOrderHttp(String productId, HttpCallBackListener<ConfigOrderBean> listener) {
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
        put("orderid",productId);
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().configOrderDetai;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ConfigOrderBean.class;
    }
}
