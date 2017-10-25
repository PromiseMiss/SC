package com.gym.shancai.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.NewProductAdapter;
import com.gym.shancai.bean.NewProductBean;
import com.gym.shancai.http.NewProductHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/30 0030.
 * 新品上市 hjd
 */

public class NewProductActivity extends BaseActivity {

    NewProductAdapter adapter;
    @BindView(R.id.gvNewMarket)
    GridView gvNewMarket;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_new_product;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        return "新品上市";
    }

    public void initData() {
        showProgressDialog();
        new NewProductHttp(new HttpCallBackListener<NewProductBean>() {
            @Override
            public void callBack(int statusCode, NewProductBean bean, String msg) {
                adapter = new NewProductAdapter(bean);
                gvNewMarket.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, NewProductBean bean, String msg) {
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
