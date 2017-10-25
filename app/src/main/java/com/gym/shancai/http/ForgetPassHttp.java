package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ForgetPassBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/6 0006.
 * 忘记密码
 */

public class ForgetPassHttp extends BaseHttp2 {

    public ForgetPassHttp(String phone, String pass, HttpCallBackListener<ForgetPassBean> listener) {
        this.listener = listener;
        put("phone", phone);
        put("pass", MD5Encode.GetMD5Code(pass));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().forgetPass;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ForgetPassBean.class;
    }
}
