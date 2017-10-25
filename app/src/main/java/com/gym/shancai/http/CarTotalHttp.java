package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CarTotalBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 选择要结算的商品后显示相应总计
 */

public class CarTotalHttp extends BaseHttp2 {

    public CarTotalHttp(HttpCallBackListener<CarTotalBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().carTotal;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CarTotalBean.class;
    }
}
