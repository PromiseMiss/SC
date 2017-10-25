package com.gym.shancai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.adapter.DayDealsAdapter;
import com.gym.shancai.bean.DayDealsBean;
import com.gym.shancai.http.DayDealsHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/29 0029.
 * 今日特价
 */

public class ActivityDayDealsFragment extends Fragment {
    View v;
    @BindView(R.id.lvDayDeals)
    ListView lvDayDeals;

    DayDealsAdapter adapter;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_act_day_deals, container, false);
       ButterKnife.bind(this, v);
        initData();
        return v;
    }

    public void initData() {
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
        MainActivity.getInstence().showProgressDialog();
        new DayDealsHttp(new HttpCallBackListener<DayDealsBean>() {
            @Override
            public void callBack(int statusCode, DayDealsBean bean, String msg) {
                adapter = new DayDealsAdapter(bean);
                lvDayDeals.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, DayDealsBean bean, String msg) {
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                srlRefresh.setRefreshing(false);
                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }
}
