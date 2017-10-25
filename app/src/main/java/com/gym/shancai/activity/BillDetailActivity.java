package com.gym.shancai.activity;

import android.view.View;
import android.widget.ExpandableListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.BillDetailAdapter;
import com.gym.shancai.bean.BillDetailBean;
import com.gym.shancai.bean.MonthlyBillDetailBean;
import com.gym.shancai.http.BillDetailHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/1 0001.
 * 账单明细
 */

public class BillDetailActivity extends BaseActivity {


    @BindView(R.id.exBillDetail)
    ExpandableListView exBillDetail;
    BillDetailAdapter adapter;
    String month, monery;
    BillDetailBean data;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_bill_detail;
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
        new BillDetailHttp(new HttpCallBackListener<BillDetailBean>() {
            @Override
            public void callBack(int statusCode, BillDetailBean bean, String msg) {
                data = bean;
                adapter = new BillDetailAdapter(bean, MainActivity.getInstence());
                exBillDetail.setAdapter(adapter);
                // 设置Group默认展开
                int groupCount = exBillDetail.getCount();
                for (int i = 0; i < groupCount; i++) {
                    exBillDetail.expandGroup(i);
                }
                exBillDetail.setGroupIndicator(null);
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onUserFail(int statusCode, BillDetailBean bean, String msg) {
                setErrorToast("您的账单空空如也~");
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
//                dismissProgressDialog();
            }
        }).post();

        exBillDetail.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                month = data.get(i).getMonth();
                HashMap parme = new HashMap();
                parme.put("type", month);
                CommonUtils.startActivity(BillDetailActivity.this, MonthBillDetailActivity.class, parme);
                return true;
            }
        });
    }

    @Override
    public void back(View view) {
        super.back(view);
        finish();
    }
}
