package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CheckPassBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 修改密码
 */

public class CheckPassHttp extends BaseHttp2 {

    public CheckPassHttp(String oldpass, String pass, HttpCallBackListener<CheckPassBean> listener) {
        this.listener = listener;
        putUserId();
        put("oldpass", MD5Encode.GetMD5Code(oldpass));
        put("pass", MD5Encode.GetMD5Code(pass));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().checkPass;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CheckPassBean.class;
    }
}
