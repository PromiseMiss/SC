package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.RankingListBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 排行榜接口
 */

public class RankingListHttp extends BaseHttp2 {

    public RankingListHttp(HttpCallBackListener<RankingListBean> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().rankingList;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return RankingListBean.class;
    }
}
