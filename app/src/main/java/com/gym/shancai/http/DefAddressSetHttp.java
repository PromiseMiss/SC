package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/11.
 */

public class DefAddressSetHttp extends BaseHttp2 {
    public DefAddressSetHttp(String addressid , HttpCallBackListener<BaseBean> listener) {
        put("addressid",addressid);
        putUserId();
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().defAddressSet;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
