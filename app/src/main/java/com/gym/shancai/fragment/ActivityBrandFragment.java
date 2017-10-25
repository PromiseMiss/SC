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
import com.gym.shancai.adapter.BrandAdapter;
import com.gym.shancai.bean.BrandBean;
import com.gym.shancai.http.BrandHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/29 0029.
 * 品牌专场
 */

public class ActivityBrandFragment extends Fragment {
    View v;
    @BindView(R.id.lvActBrand)
    ListView lvActBrand;

    BrandAdapter adapter;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_act_brand, container, false);
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

//        MainActivity.getInstence().showProgressDialog();
        new BrandHttp(new HttpCallBackListener<BrandBean>() {
            @Override
            public void callBack(int statusCode, BrandBean bean, String msg) {
                adapter = new BrandAdapter(bean);
                lvActBrand.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, BrandBean bean, String msg) {
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
