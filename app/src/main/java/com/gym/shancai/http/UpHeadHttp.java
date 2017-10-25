package com.gym.shancai.http;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.gym.shancai.bean.UpHeadBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.ImageUtils;

/**
 * Created by gym on 2017/9/8.
 */

public class UpHeadHttp extends BaseHttp2{
    public UpHeadHttp(Bitmap photo, HttpCallBackListener<UpHeadBean> listener) {
        putUserId();
        this.listener=listener;
        params.put("avatar", ImageUtils.INSTANCE.Bitmap2StrByBase64(photo));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().changeAvatar;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return UpHeadBean.class;
    }
}
