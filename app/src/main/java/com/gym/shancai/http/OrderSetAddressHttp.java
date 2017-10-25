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

public class OrderSetAddressHttp extends BaseHttp2{
    public OrderSetAddressHttp(String ordertext, String receaddressid, HttpCallBackListener<BaseBean> httpCallBackListener) {
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
        put("orderid",ordertext);
        put("addressid",receaddressid);
        this.listener=httpCallBackListener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().orderSetAddress;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
