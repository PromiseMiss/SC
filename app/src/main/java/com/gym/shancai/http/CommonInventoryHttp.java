package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CommonInventoryBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 常用清单
 */

public class CommonInventoryHttp extends BaseHttp2 {

    public CommonInventoryHttp(HttpCallBackListener<CommonInventoryBean> listener) {
        this.listener = listener;
        putUserId();

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().commonInventory;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CommonInventoryBean.class;
    }
}
