package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.CommonQuestionsBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.ImageUtils;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 常见问题
 */

public class CommonQueationsHttp extends BaseHttp2 {

    public CommonQueationsHttp(String content,String licencepicUrl, HttpCallBackListener<CommonQuestionsBean> listener) {
        this.listener = listener;
        putUserId();
        put("content", content);
        put("picurl", ImageUtils.INSTANCE.getBitmapBase64(licencepicUrl));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().commonQuestions;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return CommonQuestionsBean.class;
    }
}
