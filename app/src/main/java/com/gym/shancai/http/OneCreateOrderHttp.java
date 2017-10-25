package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CreateOrderBean;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/14.
 */

public class OneCreateOrderHttp extends BaseHttp2{
    public OneCreateOrderHttp(String goodsid,HttpCallBackListener<CreateOrderBean> lis) {
        this.listener=lis;
        putUserId();
        put("goodsid",goodsid);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().oneCreateOrder;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CreateOrderBean.class;
    }
}
