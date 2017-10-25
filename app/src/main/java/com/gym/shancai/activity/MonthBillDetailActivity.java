package com.gym.shancai.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.MonthlyBillDetailAdapter;
import com.gym.shancai.bean.MonthlyBillDetailBean;
import com.gym.shancai.http.MonthlyBillDetailHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 账单明细月账单
 */

public class MonthBillDetailActivity extends BaseActivity {

    MonthlyBillDetailAdapter adapter;
    String time;
    @BindView(R.id.lvMonthBill)
    ListView lvMonthBill;
//    @BindView(R.id.tvDate)
//    TextView tvDate;
//    @BindView(R.id.tvAllMoney)
//    TextView tvAllMoney;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_month_bill_detail;
    }

    @Override
    public String setTitleInitLayout() {
        return "账单明细";
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        time = getIntentStringExtra("type");
//        showProgressDialog();
        new MonthlyBillDetailHttp(time, new HttpCallBackListener<MonthlyBillDetailBean>() {
            @Override
            public void callBack(int statusCode, MonthlyBillDetailBean bean, String msg) {
                adapter = new MonthlyBillDetailAdapter(bean);
                lvMonthBill.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, MonthlyBillDetailBean bean, String msg) {
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

}
