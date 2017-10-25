package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RegisterSetPWBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/6 0006.
 * 注册界面设置密码
 */

public class RegisterSetPWHttp extends BaseHttp2 {

    public RegisterSetPWHttp(String phone, String password
            , HttpCallBackListener<RegisterSetPWBean> listener) {
        this.listener = listener;
        put("phone", phone);
        put("password", MD5Encode.GetMD5Code(password));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().registerSetPW;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RegisterSetPWBean.class;
    }
}
