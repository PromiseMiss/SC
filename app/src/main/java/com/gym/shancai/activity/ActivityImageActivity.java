package com.gym.shancai.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.ActivityImageAdapter;
import com.gym.shancai.bean.ActivityImageBean;
import com.gym.shancai.http.ActivityImageHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 首页活动图片页
 */

public class ActivityImageActivity extends BaseActivity {

    ActivityImageAdapter adapter;
    String aid, title;
    @BindView(R.id.lvCommodityList)
    ListView lvCommodityList;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_image_activity;
    }

    @Override
    public String setTitleInitLayout() {
        title = getIntent().getStringExtra("type");
        return title;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }

    private void initData() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        srlRefresh.setRefreshing(true);
        requestData();
    }


    private void requestData() {
        aid = getIntent().getStringExtra("key");
//        showProgressDialog();
        new ActivityImageHttp(aid, new HttpCallBackListener<ActivityImageBean>() {
            @Override
            public void callBack(int statusCode, ActivityImageBean bean, String msg) {
                adapter = new ActivityImageAdapter(bean);
                lvCommodityList.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, ActivityImageBean bean, String msg) {
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                srlRefresh.setRefreshing(false);
//                dismissProgressDialog();
            }
        }).post();
    }

}
