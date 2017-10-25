package com.gym.shancai.http;


import android.support.annotation.NonNull;

import com.gym.shancai.bean.LoginBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class LoginHttp extends BaseHttp2{
    public LoginHttp(String phone, String password,String phonetoken ,  HttpCallBackListener<LoginBean> listener) {
        this.listener=listener;
        put("phone",phone);
        put("password", MD5Encode.GetMD5Code(password));
        put("phonetoken",phonetoken);

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().login;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return LoginBean.class;
    }
}
