package com.gym.shancai.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.RefundPaymentBean;
import com.gym.shancai.http.RefundPaymentHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 收款
 */

public class ReceiptActivity extends BaseActivity {
    @BindView(R.id.tvMoneyNum)
    TextView tvMoneyNum;
    @BindView(R.id.btnReceipt)
    Button btnReceipt;

    String Id, money, iscomplete;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_receipt;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        return "收款";
    }

    public void initData() {
        Id = getIntentStringExtra("id");
        iscomplete = getIntentStringExtra("iscomplete");
        money = getIntentStringExtra("money");
        if (iscomplete.equals("1")) {
            tvMoneyNum.setText("￥" + money);
            btnReceipt.setText("付款");
        } else {
            tvMoneyNum.setText("￥" + money);
            btnReceipt.setText("收款");

        }
    }

    @OnClick({R.id.btnReceipt})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnReceipt:
                showProgressDialog();
                new RefundPaymentHttp(Id, money, new HttpCallBackListener<RefundPaymentBean>() {
                    @Override
                    public void callBack(int statusCode, RefundPaymentBean bean, String msg) {

                        CommonUtils.startActivity(ReceiptActivity.this, ReceiptOkActivity.class);
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, RefundPaymentBean bean, String msg) {
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
                break;
        }
    }

    @Override
    public void back(View view) {
        super.back(view);
        finish();
    }
}
