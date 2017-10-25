package com.gym.shancai.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.RankingListAdapter;
import com.gym.shancai.bean.RankingListBean;
import com.gym.shancai.http.RankingListHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 排行榜
 */

public class RankingListActivity extends BaseActivity {

    RankingListAdapter adapter;
    @BindView(R.id.lvRanking)
    ListView lvRanking;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_ranking_list;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        return "排行榜";
    }

    public void initData() {
        showProgressDialog();
        new RankingListHttp(new HttpCallBackListener<RankingListBean>() {
            @Override
            public void callBack(int statusCode, RankingListBean bean, String msg) {
                adapter = new RankingListAdapter(bean);
                lvRanking.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, RankingListBean bean, String msg) {
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                dismissProgressDialog();
            }
        }).post();
    }
}
