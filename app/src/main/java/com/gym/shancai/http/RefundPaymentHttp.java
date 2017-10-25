package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RefundPaymentBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by Administrator on 2017/9/15 0015.
 * 退款收款
 */

public class RefundPaymentHttp extends BaseHttp2 {

    public RefundPaymentHttp( String tradedetailid, String givemoney , HttpCallBackListener<RefundPaymentBean> listener) {
        this.listener = listener;
        putUserId();
        put("tradedetailid",tradedetailid );
        put("givemoney",givemoney );

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().refundPayment;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RefundPaymentBean.class;
    }
}
