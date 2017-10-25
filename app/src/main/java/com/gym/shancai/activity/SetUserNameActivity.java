package com.gym.shancai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.gym.shancai.R;
import com.gym.shancai.http.ChangeUserNameHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/8/25 0025.
 * 设置用户名
 */

public class SetUserNameActivity extends BaseActivity {
@BindView(R.id.et)
    EditText et;
    String oldUserName;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_set_user;
    }

    @Override
    public String setTitleInitLayout() {
        setRightButtonText("保存");
        oldUserName=getIntent().getStringExtra("username");
        et.setText(oldUserName);
        return "用户名";
    }

    @Override
    public void rightClick(View v) {
        if (oldUserName.equals(et.getText().toString())){
            finish();
        return;
        }
        if (isEmp(et.getText().toString())){
            setErrorToast("请输入一个名字");
            return;
        }
        showProgressDialog();
        new ChangeUserNameHttp(et.getText().toString(), new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {
                setOKToast("修改成功");
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
