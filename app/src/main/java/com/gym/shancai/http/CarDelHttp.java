package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CarDelBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 购物车删除商品
 */

public class CarDelHttp extends BaseHttp2 {

    public CarDelHttp(String ids,HttpCallBackListener<CarDelBean> listener) {
        this.listener = listener;
        putUserId();
        put("ids", ids);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().carDel;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CarDelBean.class;
    }
}
