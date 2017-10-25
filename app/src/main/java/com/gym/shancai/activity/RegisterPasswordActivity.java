package com.gym.shancai.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.RegisterSetPWBean;
import com.gym.shancai.http.RegisterSetPWHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 注册页面 设置密码 --hjd
 */

public class RegisterPasswordActivity extends BaseActivity {
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.etSetYouPW)
    EditText etSetYouPW;
    @BindView(R.id.etRepetitionYouPW)
    EditText etRepetitionYouPW;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvAgreement)
    TextView tvAgreement;
    @BindView(R.id.tvAgreementRead)
    TextView tvAgreementRead;

    String phone;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_register_pw;
    }

    @Override
    public String setTitleInitLayout() {
        setLeftDrawable(R.drawable.icon_close);
        return "善菜网注册";
    }

    @Override
    public void back(View view) {
        finish();
    }

    @OnClick({R.id.head_left_text_button, R.id.btnRegister})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
            case R.id.btnRegister:
                phone = getIntent().getStringExtra("phone");

                if (TextUtils.isEmpty(etSetYouPW.getText().toString()) || TextUtils.isEmpty(etRepetitionYouPW.getText().toString())) {
                    setErrorToast("请输入密码");
                    return;
                }
                if (!etSetYouPW.getText().toString().equals(etRepetitionYouPW.getText().toString())) {
                    setErrorToast("密码不一致");
                    return;
                }

                showProgressDialog();
                new RegisterSetPWHttp(phone, etSetYouPW.getText().toString()
                        , new HttpCallBackListener<RegisterSetPWBean>() {
                    @Override
                    public void callBack(int statusCode, RegisterSetPWBean bean, String msg) {
                        CommonUtils.startStoreInfoWithUserId(RegisterPasswordActivity.this, bean.getUserid());
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, RegisterSetPWBean bean, String msg) {
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
