package com.gym.shancai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gym.shancai.activity.MyOrderActivity;
import com.gym.shancai.R;
import com.gym.shancai.bean.AllIndentBean;
import com.gym.shancai.adapter.ShopOderItemsAdapter;
import com.gym.shancai.http.AllIndentHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 我的订单 —— 全部
 */

public class MyIndentAllFragemnt extends Fragment {
    View v;
    @BindView(R.id.lsIndent)
    ListView lsIndent;
    MyOrderActivity myIndentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_indent_all, container, false);
        myIndentActivity= (MyOrderActivity) getActivity();
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    AllIndentBean data;
    public void reqData() {
        new AllIndentHttp(myIndentActivity.currType,new HttpCallBackListener<AllIndentBean>() {
            @Override
            public void callBack(int statusCode, AllIndentBean bean, String msg) {
                data=bean;
                ShopOderItemsAdapter shopOderItemsAdapter = new ShopOderItemsAdapter(data);
//        ListActivity listActivity = new ListActivity();
//        listActivity.setListAdapter(shopOderItemsAdapter);
                lsIndent.setAdapter(shopOderItemsAdapter);
            }

            @Override
            public boolean onUserFail(int statusCode, AllIndentBean bean, String msg) {

                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {

            }
        }).post();

    }

    @Override
    public void onResume() {
        super.onResume();
        reqData();
    }

}
