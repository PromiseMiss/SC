package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.ProductDetailBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/12.
 */

public class ProductDetailHttp extends BaseHttp2{
    public ProductDetailHttp(boolean isKill,String id, HttpCallBackListener<ProductDetailBean> httpCallBackListener) {
        if (isKill){
            put("seckillid",id );
        }else {
            put("pid",id);
        }
        this.listener=httpCallBackListener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().goodsDetail;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ProductDetailBean.class;
    }
}
