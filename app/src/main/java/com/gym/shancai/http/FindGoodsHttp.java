package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.bean.FindGoodsBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class FindGoodsHttp extends BaseHttp2 {

    public FindGoodsHttp(HttpCallBackListener<FindGoodsBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().findGoods;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return FindGoodsBean.class;
    }
}
