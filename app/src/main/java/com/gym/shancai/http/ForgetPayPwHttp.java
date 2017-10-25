package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ForgetPayPwBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/13 0013.
 * 忘记支付密码
 */

public class ForgetPayPwHttp extends BaseHttp2 {

    public ForgetPayPwHttp(String phone, String pass, HttpCallBackListener<ForgetPayPwBean> listener) {
        this.listener = listener;
        put("phone", phone);
        put("pass", MD5Encode.GetMD5Code(pass));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().forgetPayPW;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ForgetPayPwBean.class;
    }
}
