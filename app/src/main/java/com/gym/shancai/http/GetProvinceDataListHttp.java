package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RegisterSelectAddressBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

import java.util.Set;

/**
 * Created by gym on 2017/9/8.
 */

public class GetProvinceDataListHttp extends BaseHttp2 {
    public GetProvinceDataListHttp(HttpCallBackListener<RegisterSelectAddressBean> listener) {
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().getProvinceDataList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RegisterSelectAddressBean.class;
    }
}
