package com.gym.shancai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13 0013.
 * 充值方式
 */

public class TopUpWayActivity extends BaseActivity {
    @BindView(R.id.tvWeChat)
    TextView tvWeChat;
    @BindView(R.id.tvAliPay)
    TextView tvAliPay;
    @BindView(R.id.ivOne)
    ImageView ivOne;
    @BindView(R.id.ivTwo)
    ImageView ivTwo;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_top_up_way;
    }

    @Override
    public String setTitleInitLayout() {
        return "充值方式";
    }

    public void initData() {

    }

    @OnClick({R.id.tvWeChat, R.id.tvAliPay})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tvWeChat:
                tvWeChat.setTextColor(getResources().getColor(R.color.check_font_color));
                ivTwo.setVisibility(View.GONE);
                ivOne.setVisibility(View.VISIBLE);
                tvAliPay.setTextColor(getResources().getColor(R.color.activity_font_color));
                params.put("wechat", "微信");
                finish();

                break;
            case R.id.tvAliPay:
                tvAliPay.setTextColor(getResources().getColor(R.color.check_font_color));
                ivOne.setVisibility(View.GONE);
                ivTwo.setVisibility(View.VISIBLE);
                tvWeChat.setTextColor(getResources().getColor(R.color.activity_font_color));
                params.put("alipay", "支付宝");
                finish();
                break;
        }
    }

}
