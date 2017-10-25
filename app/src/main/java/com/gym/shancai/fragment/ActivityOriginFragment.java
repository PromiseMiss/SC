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
import com.gym.shancai.adapter.OriginAdapter;
import com.gym.shancai.bean.OriginBean;
import com.gym.shancai.http.OriginHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/29 0029.
 * 产地直销
 */

public class ActivityOriginFragment extends Fragment {

    OriginAdapter adapter;


    View v;
    @BindView(R.id.lvActOrigin)
    ListView lvActOrigin;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_act_origin, container, false);
        ButterKnife.bind(this, v);
        initData();
        return v;
    }

    public void initData() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reqrestData();
            }
        });
        srlRefresh.setRefreshing(true);
        reqrestData();

    }

    private void reqrestData() {
//        MainActivity.getInstence().showProgressDialog();
        new OriginHttp(new HttpCallBackListener<OriginBean>() {
            @Override
            public void callBack(int statusCode, OriginBean bean, String msg) {
                adapter = new OriginAdapter(bean);
                lvActOrigin.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, OriginBean bean, String msg) {
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                srlRefresh.setRefreshing(false);
//                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }

}
