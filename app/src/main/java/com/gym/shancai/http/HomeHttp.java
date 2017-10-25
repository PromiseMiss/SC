package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.HomeBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/5 0005.
 *
 */

public class HomeHttp extends BaseHttp2 {

    public HomeHttp(HttpCallBackListener<HomeBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().home;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return HomeBean.class;
    }
}
