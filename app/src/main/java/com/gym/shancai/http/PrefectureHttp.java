package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.bean.FindGoodsBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/8 0008.
 * 预制菜专区
 */

public class PrefectureHttp extends BaseHttp2 {

    public PrefectureHttp(HttpCallBackListener<FindGoodsBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().prefecture;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return FindGoodsBean.class;
    }
}
