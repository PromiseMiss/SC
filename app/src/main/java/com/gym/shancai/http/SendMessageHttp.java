package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.SendMessageBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class SendMessageHttp extends BaseHttp2 {

    public SendMessageHttp(String phone, HttpCallBackListener<SendMessageBean> listener) {
        this.listener = listener;
        put("phone", phone);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().sendMessage;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return SendMessageBean.class;
    }
}
