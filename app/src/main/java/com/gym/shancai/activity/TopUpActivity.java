package com.gym.shancai.activity;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.MyWalletTopUpBean;
import com.gym.shancai.bean.WxRechargeBean;
import com.gym.shancai.http.MyWalletTopUpHttp;
import com.gym.shancai.http.WxRechargeHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.MathUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 充值 -hjd
 */

public class TopUpActivity extends BaseActivity {
    @BindView(R.id.tvTopUpAccount)
    TextView tvTopUpAccount;
    @BindView(R.id.tvTopUpMode)
    TextView tvTopUpMode;
    @BindView(R.id.llTopUpStyle)
    LinearLayout llTopUpStyle;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btnTopUp)
    Button btnTopUp;
    @BindView(R.id.llActivity)
    LinearLayout llActivity;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_top_up;
    }

    @Override
    public String setTitleInitLayout() {
        MathUtils.setPricePoint(et);
        return "充值";
    }

    @OnClick({R.id.tvTopUpMode, R.id.btnTopUp})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.tvTopUpMode:
                setErrorToast("暂时只有微信充值");
//                CommonUtils.startActivity(this, TopUpWayActivity.class);
                break;
            case R.id.btnTopUp:
                showProgressDialog();
                new WxRechargeHttp(et.getText().toString(), 1, "", new HttpCallBackListener<WxRechargeBean>() {
                    @Override
                    public void callBack(int statusCode, WxRechargeBean bean, String msg) {
                        CommonUtils.WxRecharge(TopUpActivity.this,bean);
                    }

                    @Override
                    public boolean onUserFail(int statusCode, WxRechargeBean bean, String msg) {
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
    protected void onResume() {
        super.onResume();
        requestData();
    }
    MyWalletTopUpBean data;
    private void requestData() {
        showProgressDialog();
        new MyWalletTopUpHttp(new HttpCallBackListener<MyWalletTopUpBean>() {
            @Override
            public void callBack(int statusCode, MyWalletTopUpBean bean, String msg) {
                data=bean;
                tvTopUpAccount.setText(bean.getPhone());
                llActivity.removeAllViews();
                for (int i = 0 ;i<bean.getActivity().size();i++){
                    Button bt=new Button(TopUpActivity.this);
                    bt.setTextSize(12);
                    bt.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(MathUtils.dip2px(84),MathUtils.dip2px(64));
                    lp.setMargins(20,0,20,0);
                    bt.setLayoutParams(lp);
                    bt.setBackgroundResource(R.drawable.btn_top_up_style);
                    bt.setTextColor(ContextCompat.getColor(TopUpActivity.this,R.color.check_font_color));
                    bt.setText(("￥"+bean.getActivity().get(i).getMoney())+"\n"+"(赠送￥"+bean.getActivity().get(i).getSendmoney()+")");
                    bt.setTag(bean.getActivity().get(i).getMoney());
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            et.setText((String) view.getTag());
                        }
                    });
                    llActivity.addView(bt);
                }
            }

            @Override
            public boolean onUserFail(int statusCode, MyWalletTopUpBean bean, String msg) {
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
