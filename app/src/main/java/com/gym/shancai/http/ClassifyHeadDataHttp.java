package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ClassifyHeadBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyHeadDataHttp extends BaseHttp2{
    public ClassifyHeadDataHttp(HttpCallBackListener<ClassifyHeadBean> listener) {
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().classifyHeadData;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ClassifyHeadBean.class;
    }
}
