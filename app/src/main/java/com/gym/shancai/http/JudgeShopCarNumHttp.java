package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/15.
 */

public class JudgeShopCarNumHttp extends BaseHttp2 {
    /**
     *
     * @param content（例如：1#6   <只能一条信息>）
     * @param listener
     */
    public JudgeShopCarNumHttp(String content, HttpCallBackListener<BaseBean> listener) {
        this.listener=listener;
        putUserId();
        put("content",content);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().judgeShopCarNum;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
