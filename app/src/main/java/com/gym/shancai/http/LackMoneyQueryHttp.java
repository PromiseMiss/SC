package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.LackMoneyQueryBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 差款查询
 */

public class LackMoneyQueryHttp extends BaseHttp2 {

    public LackMoneyQueryHttp(HttpCallBackListener<LackMoneyQueryBean> listener) {
        putUserId();
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().lackMoneyQuery;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return LackMoneyQueryBean.class;
    }
}
