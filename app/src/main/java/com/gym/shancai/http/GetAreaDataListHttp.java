package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RegisterSelectAddressBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/8.
 */

public class GetAreaDataListHttp extends BaseHttp2 {
    public GetAreaDataListHttp(String citynum ,HttpCallBackListener<RegisterSelectAddressBean> listener) {
        this.listener=listener;
        put("citynum",citynum);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().getAreaDataList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RegisterSelectAddressBean.class;
    }
}
