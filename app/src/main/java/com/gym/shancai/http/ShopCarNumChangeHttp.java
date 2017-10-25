package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/15.
 */

public class ShopCarNumChangeHttp extends BaseHttp2 {
    /**
     *
     * @param content 购物车id和对应数量（例如：1#6,2#13,3#12）
     */
    public ShopCarNumChangeHttp(String content, HttpCallBackListener<BaseBean> listener) {
        this.listener=listener;
        putUserId();
        put("content",content);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().shopCarNumChange;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
