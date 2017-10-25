package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.BillDetailBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 账单明细
 */

public class BillDetailHttp extends BaseHttp2 {

    public BillDetailHttp(HttpCallBackListener<BillDetailBean> listener) {
        this.listener = listener;
        putUserId();
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().billDetail;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BillDetailBean.class;
    }
}
