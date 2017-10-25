package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.DetailBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 明细
 */

public class DetailHttp extends BaseHttp2 {

    public DetailHttp(HttpCallBackListener<DetailBean> listener) {
        this.listener = listener;
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().detail;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return DetailBean.class;
    }
}
