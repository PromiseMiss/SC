package com.gym.shancai.http;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.gym.shancai.bean.ActivityImageBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class ActivityImageHttp extends BaseHttp2 {

    public ActivityImageHttp(String id, HttpCallBackListener<ActivityImageBean> listener) {
        this.listener = listener;
        put("id", id);
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().activityImage;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return ActivityImageBean.class;
    }
}
