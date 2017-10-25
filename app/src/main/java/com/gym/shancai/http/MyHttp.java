package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.MyBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/8.
 * 我的接口
 */

public class MyHttp extends BaseHttp2{
    public MyHttp(HttpCallBackListener<MyBean> listener) {
        this.listener=listener;
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().my;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return MyBean.class;
    }
}
