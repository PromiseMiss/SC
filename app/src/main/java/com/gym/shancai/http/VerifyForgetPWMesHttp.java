package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.VerifyForgetPWMesBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/6 0006.
 * 忘记密码    验证码
 */

public class VerifyForgetPWMesHttp extends BaseHttp2 {

    public VerifyForgetPWMesHttp(String phone, String yzmcode
            , HttpCallBackListener<VerifyForgetPWMesBean> listener) {
        this.listener = listener;
        put("phone", phone);
        put("yzmcode", yzmcode);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().verifyForgetPWMes;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return VerifyForgetPWMesBean.class;
    }
}
