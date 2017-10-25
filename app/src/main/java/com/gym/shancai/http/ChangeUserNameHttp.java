package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/8.
 */

public class ChangeUserNameHttp extends BaseHttp2 {
    public ChangeUserNameHttp(String username, HttpCallBackListener<BaseBean> listener) {
        put("username",username);
        this.listener=listener;
        put("uid", SharedPreferencesSetting.getInstance().getUserId());
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().changeUserName;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
