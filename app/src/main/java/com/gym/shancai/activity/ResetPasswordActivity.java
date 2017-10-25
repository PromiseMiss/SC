package com.gym.shancai.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.ForgetPassBean;
import com.gym.shancai.http.ForgetPassHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 重置密码
 */

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.etSetLogPW)
    EditText etSetLogPW;
    @BindView(R.id.etResPass)
    EditText etResPass;

    String tel;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_reset_password;
    }

    @Override
    public String setTitleInitLayout() {
        setLeftDrawable(R.drawable.icon_close);
        setRightButtonText("下一步");
        return "重置密码";
    }

    @OnClick({R.id.head_right_text_button})
    public void OnClick(View view) {

        switch (view.getId()) {
            case R.id.head_right_text_button:
                if (TextUtils.isEmpty(etSetLogPW.getText().toString()) || TextUtils.isEmpty(etResPass.getText().toString())) {
                    setErrorToast("请输入密码");
                }
                if (!etSetLogPW.getText().toString().equals(etResPass.getText().toString())) {
                    setErrorToast("输入的密码不一致");
                }

                showProgressDialog();
                tel = getIntentStringExtra("type");
                new ForgetPassHttp(tel, etResPass.getText().toString()
                        , new HttpCallBackListener<ForgetPassBean>() {
                    @Override
                    public void callBack(int statusCode, ForgetPassBean bean, String msg) {
                        setOKToast("修改成功");
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, ForgetPassBean bean, String msg) {
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
}
