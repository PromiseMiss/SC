package com.gym.shancai.activity;

import android.view.View;
import android.widget.ExpandableListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.LackMoneyQueryAdapter;
import com.gym.shancai.bean.LackMoneyQueryBean;
import com.gym.shancai.http.LackMoneyQueryHttp;
import com.gym.shancai.http.base.HttpCallBackListener;


import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/1 0001.
 * 差款查询
 */

public class LackMoneyActivity extends BaseActivity {

    @BindView(R.id.exLackMoney)
    ExpandableListView exLackMoney;
    LackMoneyQueryAdapter adapter;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_lack_money_query;
    }

    @Override
    public String setTitleInitLayout() {
        return "差款查询";
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {

        new LackMoneyQueryHttp(new HttpCallBackListener<LackMoneyQueryBean>() {
            @Override
            public void callBack(int statusCode, LackMoneyQueryBean bean, String msg) {
                adapter = new LackMoneyQueryAdapter(bean, MainActivity.getInstence());
                exLackMoney.setAdapter(adapter);
                //设置默认展开所有
                int groupCount = exLackMoney.getCount();
                for (int i = 0; i < groupCount; i++) {
                    exLackMoney.expandGroup(i);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onUserFail(int statusCode, LackMoneyQueryBean bean, String msg) {
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {

            }
        }).post();

        exLackMoney.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                view.setEnabled(false); //设置Group禁止点击
                return true;
            }
        });
    }
}
