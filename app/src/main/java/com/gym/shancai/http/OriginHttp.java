package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.OriginBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 产地直销
 */

public class OriginHttp extends BaseHttp2 {

    public OriginHttp(HttpCallBackListener<OriginBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().origin;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return OriginBean.class;
    }
}
