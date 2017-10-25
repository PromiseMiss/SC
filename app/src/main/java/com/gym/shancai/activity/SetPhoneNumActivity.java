package com.gym.shancai.activity;


import android.view.View;
import android.widget.EditText;

import com.gym.shancai.R;
import com.gym.shancai.http.ChangePhoneHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 设置手机号界面
 */

public class SetPhoneNumActivity extends BaseActivity {
    @BindView(R.id.et)
    EditText et;
    String oldString;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_set_phone_number;
    }

    @Override
    public String setTitleInitLayout() {
        setRightButtonText("保存");
//        oldString = getIntentStringExtra("phone");
        et.setText(oldString);
        return "手机号";
    }

    @Override
    public void rightClick(View v) {
        if (et.getText().toString().equals(oldString)) {
            finish();
            return;
        }
        showProgressDialog();
        new ChangePhoneHttp(et.getText().toString(), new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {
                finish();
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
}
