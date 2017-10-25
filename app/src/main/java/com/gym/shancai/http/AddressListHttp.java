package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.AddressListBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/11.
 */

public class AddressListHttp extends BaseHttp2{
    public AddressListHttp(HttpCallBackListener<AddressListBean> beanHttpCallBackListener) {
        this.listener=beanHttpCallBackListener;
        putUserId();

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().addressList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return AddressListBean.class;
    }
}
