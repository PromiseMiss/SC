package com.gym.shancai.activity;

import android.view.View;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/30 0030.
 * 收款成功 hjd
 */

public class ReceiptOkActivity extends BaseActivity {

    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.tvBackWallet)
    TextView tvBackWallet;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_receipt_ok;
    }

    @Override
    public String setTitleInitLayout() {
        return "收款";
    }

    @OnClick({R.id.tvBackWallet})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tvBackWallet:
                CommonUtils.startActivity(ReceiptOkActivity.this, MyWalletActivity.class);
                finish();
                break;
        }
    }
}
