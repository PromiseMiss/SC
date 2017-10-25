package com.gym.shancai.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.Constant;
import com.gym.shancai.R;
import com.gym.shancai.adapter.ConfigCommonOrderAdapter;
import com.gym.shancai.bean.ConfigOrderBean;
import com.gym.shancai.bean.WxRechargeBean;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.dialog.BottomListDialog;
import com.gym.shancai.dialog.PayDialog;
import com.gym.shancai.http.ConfigOrderHttp;
import com.gym.shancai.http.OrderInspectionHttp;
import com.gym.shancai.http.PayOrderHttp;
import com.gym.shancai.http.WxRechargeHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.MathUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 订单确认
 */

public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvCommit)
    TextView tvCommit;

    BottomListDialog payWayDialog;

    View headView;
    View footView;

    private int type;

    //headView
    private TextView tvReplenishPrice;
    TextView tvName;
    TextView tvPhone;
    TextView tvAddress;
    RelativeLayout rlAddress;

    int getCurrPayWay = Constant.PAYWAY_YUE;

    ConfigCommonOrderAdapter adapter;


    //footView
    TextView tvPostPrice;
    TextView tvPayWay;
    LinearLayout llPayWay;
    /**
     * 当前支付方式 默认家宝
     */
    public static final int PAYWAY_JIABAO = 1;
    public static final int PAYWAY_ZHUANJIN = 2;
    int currPayWay = PAYWAY_JIABAO;
    float priceAll;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_indent_affirm;
    }

    @Override
    public String setTitleInitLayout() {

        headView = LayoutInflater.from(this).inflate(R.layout.head_config_order, null);
        footView = LayoutInflater.from(this).inflate(R.layout.foot_config_order, null);
        tvName = (TextView) headView.findViewById(R.id.tvName);
//        tvZipCode = (TextView) headView.findViewById(R.id.tv_zip_code);
        tvPhone = (TextView) headView.findViewById(R.id.tvPhone);
        tvAddress = (TextView) headView.findViewById(R.id.tvAddress);
        rlAddress = headView.findViewById(R.id.rlAddress);
        tvReplenishPrice = headView.findViewById(R.id.tvReplenishPrice);
        rlAddress.setOnClickListener(this);
        payWayDialog = new BottomListDialog(this);
        payWayDialog.addItem("余额支付", Constant.PAYWAY_YUE);
        payWayDialog.addItem("微信支付", Constant.PAYWAY_WEIXIN);
        payWayDialog.setonSelectListener(new BottomListDialog.SelectListener() {
            @Override
            public void onSelect(int position, View view, Object tag) {
                currPayWay = (int) tag;
                tvPayWay.setText(payWayDialog.getItemName(position));
            }
        });

        tvPostPrice = footView.findViewById(R.id.tvPostPrice);
        llPayWay = footView.findViewById(R.id.llPayWay);
        llPayWay.setOnClickListener(this);
        tvPayWay = footView.findViewById(R.id.tvPayWay);
        tvPayWay.setText(payWayDialog.getItemName(0));
        lv.addHeaderView(headView);
        lv.addFooterView(footView);
        return "订单确认";
    }

    @Override
    protected void onResume() {
        super.onResume();
        reqData();
    }

    ConfigOrderBean data;

    private void reqData() {
        showProgressDialog();
        new ConfigOrderHttp(getIntent().getStringExtra("ordertext"), new HttpCallBackListener<ConfigOrderBean>() {
            @Override
            public void callBack(int statusCode, ConfigOrderBean bean, String msg) {
                data = bean;
                adapter = new ConfigCommonOrderAdapter(ConfirmOrderActivity.this, data);
                if (bean.getAddress().getAddressinfo() == null) {
                        setToastAutoCancel(false);
                    tvAddress.setText("未设置地址");
                } else {
                    tvName.setText(bean.getAddress().getReceivename());
                    tvPhone.setText(bean.getAddress().getPhonenum());
                    tvAddress.setText(bean.getAddress().getAddressinfo());
                }
//                        priceAll = 0;
//                        for (int i = 0; i < data.getOrderlist().size(); i++) {
//                            priceAll += data.getOrderlist().get(i).getOrderprice();
////                    priceAll += data.getOrderlist().get(i).getOrderpostprice();
//                        }

                priceAll = Float.parseFloat(bean.getTotalmoney() + "");
                tvPrice.setText(MathUtils.get4down5up2d(priceAll));
                tvPostPrice.setText("￥:" + data.getPostprice());
                tvReplenishPrice.setText("￥：" + data.getGivemoney());
                lv.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, ConfigOrderBean bean, String msg) {
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

    @OnClick({R.id.tvCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llPayWay:
                payWayDialog.show();
                break;
            case R.id.rlAddress:
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);
                intent.putExtra("isSelect", true);
                intent.putExtra("ordertext", getIntent().getStringExtra("ordertext"));
                startActivity(intent);
                break;
            case R.id.tvCommit:
                orderInspectionAndPay();
                break;
        }
    }

    private void orderInspectionAndPay() {
        if (TextUtils.isEmpty(data.getAddress().getAddressinfo())) {
            setErrorToast("请选择一个收货地址");
            return;
        }
        showProgressDialog();
        new OrderInspectionHttp(getIntent().getStringExtra("ordertext"), new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {
                switch (currPayWay) {
                    case Constant.PAYWAY_YUE:
                        yuEPayDialog();
                        break;
                    case Constant.PAYWAY_WEIXIN:
                        wXPay();
                        break;
                }
            }

            @Override
            public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
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

    private void wXPay() {
        showProgressDialog();
        new WxRechargeHttp(data.getTotalmoney() + "", 2, getIntent().getStringExtra("ordertext"), new HttpCallBackListener<WxRechargeBean>() {
            @Override
            public void callBack(int statusCode, WxRechargeBean bean, String msg) {

                CommonUtils.WxRecharge(ConfirmOrderActivity.this, bean);
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
                setErrorToast("请继续完成支付");
                setToastAutoCancel(false);
                dismissProgressDialog();
                finish();
//                CommonUtils.startActivity(ConfirmOrderActivity.this, MyOrderActivity.class);

            }
        }).post();
    }

    PayDialog payDialog;
    BaseDialog tipDialog;

    /**
     * 调出支付窗口
     */
    public void yuEPayDialog() {
        payDialog = new PayDialog(ConfirmOrderActivity.this, priceAll, Constant.PAYWAY_YUE, new PayDialog.OnDialogPasswordFinishListener() {
            @Override
            public void onInputFinish(String password) {
                new PayOrderHttp(getIntent().getStringExtra("ordertext"), password, new HttpCallBackListener() {
                    @Override
                    public void callBack(int statusCode, Object bean, String msg) {
                        setOKToast("支付成功");
                        finish();
                        if (getIntent().getBooleanExtra("isOrder", false)) {

                        } else {
                            CommonUtils.startActivity(ConfirmOrderActivity.this, MyOrderActivity.class);
                        }
                    }

                    @Override
                    public boolean onUserFail(int statusCode, Object bean, String msg) {
                        // 1：交易成功 -1：余额不足 -2：交易失败 -3：支付密码错误
                        if (statusCode == -3) {
                            setToastAutoCancel(false);
                            setErrorToast("密码错误");
                            //-6密码错误
                            onClick(findViewById(R.id.tvCommit));
                            return true;
                        }


//                        setErrorToast( "密码错误");

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

            @Override
            public void onFirstSet() {

            }
        });
        payDialog.show();

    }
}
