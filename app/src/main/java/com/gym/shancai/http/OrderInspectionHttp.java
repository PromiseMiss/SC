package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/15.
 */

public class OrderInspectionHttp extends BaseHttp2{
    public OrderInspectionHttp(String orderid, HttpCallBackListener<BaseBean> listener) {
        this.listener=listener;
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
        put("orderid",orderid);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().orderInspection;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
