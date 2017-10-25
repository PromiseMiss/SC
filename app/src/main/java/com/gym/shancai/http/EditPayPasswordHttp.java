package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.EditPayPasswordBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class EditPayPasswordHttp extends BaseHttp2 {

    public EditPayPasswordHttp(String oldpass, String pass, HttpCallBackListener<EditPayPasswordBean> listener) {
        this.listener = listener;
        put("oldpass", MD5Encode.GetMD5Code(oldpass));
        put("pass", MD5Encode.GetMD5Code(pass));
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().editPass;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return EditPayPasswordBean.class;
    }
}
