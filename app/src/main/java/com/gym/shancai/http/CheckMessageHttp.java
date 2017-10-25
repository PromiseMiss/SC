package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CheckMessageBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class CheckMessageHttp extends BaseHttp2 {

    public CheckMessageHttp(String phone, String yzmcode, HttpCallBackListener<CheckMessageBean> listener) {
        this.listener = listener;
        put("phone", phone);
        put("yzmcode", yzmcode);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().checkMessage;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CheckMessageBean.class;
    }
}
