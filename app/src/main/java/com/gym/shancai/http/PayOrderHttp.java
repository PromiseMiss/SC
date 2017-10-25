package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.MD5Encode;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/15.
 */

public class PayOrderHttp extends BaseHttp2{
    public PayOrderHttp(String ordertext, String password, HttpCallBackListener httpCallBackListener) {
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
        put("paypassword", MD5Encode.GetMD5Code(password));
        put("orderid",ordertext);
        this.listener=httpCallBackListener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().pay;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
