package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/14.
 */

public class TipOrderHttp extends BaseHttp2{
    public TipOrderHttp(String orderid, HttpCallBackListener listener) {
        put("orderid",orderid);
        put("uid", SharedPreferencesSetting.getInstance().getUserId());
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().tipOrder;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
