package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.DayDealsBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class DayDealsHttp extends BaseHttp2 {

    public DayDealsHttp(HttpCallBackListener<DayDealsBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().specialToday;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return DayDealsBean.class;
    }
}
