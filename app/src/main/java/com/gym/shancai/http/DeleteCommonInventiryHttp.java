package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/14.
 */

public class DeleteCommonInventiryHttp extends BaseHttp2 {
    public DeleteCommonInventiryHttp(String usersid, String ids, HttpCallBackListener<BaseBean> listener) {
        put("usersid", usersid);
        put("ids", ids);
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().delCommonList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
