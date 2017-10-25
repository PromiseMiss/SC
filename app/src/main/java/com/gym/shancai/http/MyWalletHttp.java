package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.MyWalletBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class MyWalletHttp extends BaseHttp2 {

    public MyWalletHttp(HttpCallBackListener<MyWalletBean> listener) {
        this.listener = listener;
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().myWallet;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return MyWalletBean.class;
    }
}
