package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.NewProductBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 新品上市
 */

public class NewProductHttp extends BaseHttp2 {

    public NewProductHttp(HttpCallBackListener<NewProductBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().newProduct;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return NewProductBean.class;
    }
}
