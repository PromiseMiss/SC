package com.gym.shancai.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.MyWalletBean;
import com.gym.shancai.http.MyWalletHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 我的钱包-hjd
 */

public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.head_right_button)
    ImageView headRightButton;
    @BindView(R.id.tvTopUp)
    TextView tvTopUp;
    @BindView(R.id.tvMoneyQuery)
    TextView tvMoneyQuery;
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;

    MyWalletBean data;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public String setTitleInitLayout() {
        setRightButtonText("明细");
        return "我的钱包";
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        params = new HashMap();
        showProgressDialog();
        new MyWalletHttp(new HttpCallBackListener<MyWalletBean>() {
            @Override
            public void callBack(int statusCode, MyWalletBean bean, String msg) {
                data = bean;
                tvAllMoney.setText(bean.getBalance());
            }

            @Override
            public boolean onUserFail(int statusCode, MyWalletBean bean, String msg) {
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

    @OnClick({R.id.tvTopUp, R.id.tvMoneyQuery, R.id.head_left_text_button, R.id.head_right_text_button})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
            case +R.id.head_right_text_button:
                params.put("type", data.getBalance());
                CommonUtils.startActivity(this, DetailActivity.class, params);
                break;
            case R.id.tvTopUp:
//                params.put("type", tvAllMoney);
                CommonUtils.startActivity(this, TopUpActivity.class);
                break;
            case R.id.tvMoneyQuery:
                CommonUtils.startActivity(this, LackMoneyActivity.class);
                break;
        }

    }
}
