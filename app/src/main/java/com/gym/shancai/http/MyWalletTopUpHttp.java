package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.MyWalletTopUpBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 充值活动页面
 */

public class MyWalletTopUpHttp extends BaseHttp2 {

    public MyWalletTopUpHttp(HttpCallBackListener<MyWalletTopUpBean> listener) {
        this.listener = listener;
        putUserId();

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().myWalletTopUp;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return MyWalletTopUpBean.class;
    }
}
