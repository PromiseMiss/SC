package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CatIndexListBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 购物车列表
 */

public class CatIndexListHttp extends BaseHttp2 {

    public CatIndexListHttp(HttpCallBackListener<CatIndexListBean> listener) {
        this.listener = listener;
        put("uid", SharedPreferencesSetting.getInstance().getUserId());
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().cartIndexList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CatIndexListBean.class;
    }
}
