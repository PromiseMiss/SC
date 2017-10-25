package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/9.
 */

public class ChangePhoneHttp extends BaseHttp2 {
    public ChangePhoneHttp(String changePhone, HttpCallBackListener<BaseBean> listener) {
        putUserId();
        put("changePhone",changePhone);
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().changePhone;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
