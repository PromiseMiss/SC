package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/8 0008.
 * 清凉一夏
 */

public class CoolSummerHttp extends BaseHttp2 {

    public CoolSummerHttp(HttpCallBackListener<CoolSummerBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().coolSummer;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CoolSummerBean.class;
    }
}
