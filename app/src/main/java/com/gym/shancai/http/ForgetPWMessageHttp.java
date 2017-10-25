package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ForgetPWMessageBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/6 0006.
 * 忘记密码登录页的
 */

public class ForgetPWMessageHttp extends BaseHttp2 {

    public ForgetPWMessageHttp(String phone, HttpCallBackListener<ForgetPWMessageBean> listener) {
        this.listener = listener;
        put("phone", phone);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().forgetPWMessage;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ForgetPWMessageBean.class;
    }
}
