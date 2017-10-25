package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.SearchBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class SearchHttp extends BaseHttp2 {

    public SearchHttp(String prodname, HttpCallBackListener<SearchBean> listener) {
        this.listener = listener;
        put("prodname", prodname);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().search;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return SearchBean.class;
    }
}
