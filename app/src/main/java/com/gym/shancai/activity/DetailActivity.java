package com.gym.shancai.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.DetailAdapter;
import com.gym.shancai.bean.DetailBean;
import com.gym.shancai.http.DetailHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 我的钱包--明细-hjd
 */

public class DetailActivity extends BaseActivity {
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.lvDetail)
    ListView lvDetail;

    DetailAdapter adapter;
    TextView tvBalance;
    String balance;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_detail_wallet;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        return "明细";
    }

    public void initData() {
        final View headView = LayoutInflater.from(MainActivity.getInstence()).inflate(R.layout.item_detail_head, null);
        tvBalance = headView.findViewById(R.id.tvBalance);
        lvDetail.addHeaderView(headView);

        balance = getIntentStringExtra("type");

        showProgressDialog();
        new DetailHttp(new HttpCallBackListener<DetailBean>() {
            @Override
            public void callBack(int statusCode, DetailBean bean, String msg) {
                adapter = new DetailAdapter(bean);
                lvDetail.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                tvBalance.setText(balance);
            }

            @Override
            public boolean onUserFail(int statusCode, DetailBean bean, String msg) {
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


    @OnClick({R.id.head_left_text_button})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
        }
    }

    @Override
    public void back(View view) {
        super.back(view);
        finish();
    }
}
