package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.MonthlyBillDetailBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class MonthlyBillDetailHttp extends BaseHttp2 {

    public MonthlyBillDetailHttp(String month, HttpCallBackListener<MonthlyBillDetailBean> listener) {
        put("month", month);
        putUserId();
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().monthDetail;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return MonthlyBillDetailBean.class;
    }
}
