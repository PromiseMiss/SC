package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.AllIndentBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by gym on 2017/9/13.
 */

public class AllIndentHttp extends BaseHttp2{
    int currType;
    public AllIndentHttp(int currType, HttpCallBackListener<AllIndentBean> listener) {
        this.currType=currType;
        if (currType==0){

        }else {
            //(1：待付款 2：待发货 3：待收货
            switch (currType){
                case 1:
                    put("status","1");
                    break;
                case 2:
                    put("status","2");
                    break;
                case 3:
                    put("status","3");
                    break;
            }

        }
        putUserId();
        this.listener=listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        if (currType==0)
        return ServerUrl.getInstance().allIndent;
        else
            return ServerUrl.getInstance().kindIndent;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return AllIndentBean.class;
    }
}
