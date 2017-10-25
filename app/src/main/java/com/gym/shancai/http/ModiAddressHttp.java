package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/11.
 */

public class ModiAddressHttp extends BaseHttp2 {
    /**
     *
     @apiParam {String} userid 用户id
     @apiParam {String} phonetoken 异地登录标记
     @apiParam {String} addressid 地址id
     @apiParam {string} address 地址(选填)
     @apiParam {string} addressinfo 具体地址(选填)
     @apiParam {string} receivename 收货人姓名(选填)
     @apiParam {string} phonenum 电话(选填)
     */
    public ModiAddressHttp(String addressid , String address , String  addressinfo , String receivename , String phonenum , HttpCallBackListener<BaseBean> listener) {
        this.listener=listener;
        putUserId();
        put("addressid",addressid);
        put("address",address);
        put("addressinfo",addressinfo);
        put("receivename",receivename);
        put("phonenum",phonenum);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().modifyAddress;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return BaseBean.class;
    }
}
