package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CarDelBean;
import com.gym.shancai.bean.CreateOrderBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/14.
 */

public class CarCreateOredeHttp extends BaseHttp2{
    public CarCreateOredeHttp(String ids, HttpCallBackListener<CreateOrderBean> lis) {
        putUserId();
        put("cartid",ids);
        this.listener=lis;

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().carCreateOrder;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CreateOrderBean.class;
    }
}
