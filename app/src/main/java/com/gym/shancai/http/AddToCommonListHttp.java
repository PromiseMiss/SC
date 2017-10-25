package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/12.
 */

public class AddToCommonListHttp extends BaseHttp2{
    public AddToCommonListHttp(String productid, HttpCallBackListener<BaseBean> listener) {
        this.listener=listener;
        put("productid",productid);
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().addToCommonList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
