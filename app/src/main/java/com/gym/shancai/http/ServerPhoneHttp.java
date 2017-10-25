package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ServerPhoneBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/5 0005.
 * 客服中心
 */

public class ServerPhoneHttp extends BaseHttp2 {

    public ServerPhoneHttp(HttpCallBackListener<ServerPhoneBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().serverPhone;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ServerPhoneBean.class;
    }
}
