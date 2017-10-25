package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ClassifyLeftBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyLeftDataHttp extends BaseHttp2{
    public ClassifyLeftDataHttp(String pid,HttpCallBackListener<ClassifyLeftBean> httpCallBackListener) {
        this.listener=httpCallBackListener;
        put("pid",pid);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().classifyLeftData;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ClassifyLeftBean.class;
    }
}
