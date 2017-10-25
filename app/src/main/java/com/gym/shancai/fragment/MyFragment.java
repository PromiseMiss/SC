package com.gym.shancai.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.activity.CommonInventoryActivity;
import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.activity.MyOrderActivity;
import com.gym.shancai.activity.MyWalletActivity;
import com.gym.shancai.activity.PersonalInfoActivity;
import com.gym.shancai.R;
import com.gym.shancai.activity.ServiceCenterActivity;
import com.gym.shancai.activity.SettingActivity;
import com.gym.shancai.bean.MyBean;
import com.gym.shancai.http.MyHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 我的界面-hjd
 */

public class MyFragment extends Fragment {


    View v;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvSetUp)
    TextView tvSetUp;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.ivSetting)
    ImageView ivSetting;
    @BindView(R.id.ivMyMessage)
    ImageView ivMyMessage;
    @BindView(R.id.ivUserIcon)
    ImageView ivUserIcon;
    @BindView(R.id.tvStayPay)
    TextView tvStayPay;
    @BindView(R.id.tvStayShipments)
    TextView tvStayShipments;
    @BindView(R.id.tvStayGoods)
    TextView tvStayGoods;
    @BindView(R.id.tvMyIndent)
    TextView tvMyIndent;
    @BindView(R.id.tvCommon)
    TextView tvCommon;
    @BindView(R.id.tvWallet)
    TextView tvWallet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my, container, false);
     ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.tvUserName, R.id.tvSetUp, R.id.tvService, R.id.tvCommon, R.id.ivUserIcon
            , R.id.tvWallet, R.id.tvMyIndent, R.id.tvStayPay, R.id.tvStayShipments, R.id.tvStayGoods
            , R.id.ivSetting})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSetting:
                CommonUtils.startActivity(MainActivity.getInstence(), SettingActivity.class);
                break;
            case R.id.tvUserName:
                CommonUtils.startActivity(MainActivity.getInstence(), PersonalInfoActivity.class);
                break;
            case R.id.tvSetUp:
                CommonUtils.startActivity(MainActivity.getInstence(), SettingActivity.class);
                break;
            case R.id.tvService:
                CommonUtils.startActivity(MainActivity.getInstence(), ServiceCenterActivity.class);
                break;
            case R.id.tvCommon:
                CommonUtils.startActivity(MainActivity.getInstence(), CommonInventoryActivity.class);
                break;
            case R.id.ivUserIcon:
//                CommonUtils.startActivity(MainActivity.getInstence(), LoginActivity.class);
                break;
            case R.id.tvWallet:
                CommonUtils.startActivity(MainActivity.getInstence(), MyWalletActivity.class);
                break;
            case R.id.tvMyIndent:
                CommonUtils.startActivity(MainActivity.getInstence(), MyOrderActivity.class);
                break;
            //待付款
            case R.id.tvStayPay:
                CommonUtils.startActivity(getContext(), MyOrderActivity.class);
                break;
            //待发货
            case R.id.tvStayShipments:
                CommonUtils.startActivity(getContext(), MyOrderActivity.class);
                break;
            //待收货
            case R.id.tvStayGoods:
                CommonUtils.startActivity(getContext(), MyOrderActivity.class);
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    MyBean data;

    private void requestData() {
        MainActivity.getInstence().showProgressDialog();
        new MyHttp(new HttpCallBackListener<MyBean>() {
            @Override
            public void callBack(int statusCode, MyBean bean, String msg) {
                data = bean;
                tvUserName.setText(data.getUsername());
                ImageUtils.INSTANCE.loadImageCircleWithStork(MainActivity.getInstence(), data.getAvatar().toString(), ivUserIcon, 2.6f, Color.WHITE, true);
            }

            @Override
            public boolean onUserFail(int statusCode, MyBean bean, String msg) {
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }
}
