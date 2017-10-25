package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ClassifyRightBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyRightDataHttp extends BaseHttp2{
    public ClassifyRightDataHttp(String tid , HttpCallBackListener<ClassifyRightBean> listener) {
        this.listener=listener;
        put("tid",tid );
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().classifyRightData;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ClassifyRightBean.class;
    }
}
