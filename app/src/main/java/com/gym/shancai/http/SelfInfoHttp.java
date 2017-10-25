package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.SelfInfoBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/7 0007.
 * 个人信息
 */

public class SelfInfoHttp extends BaseHttp2 {

    public SelfInfoHttp(HttpCallBackListener<SelfInfoBean> listener) {
        this.listener = listener;
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().selfInfo;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return SelfInfoBean.class;
    }
}
