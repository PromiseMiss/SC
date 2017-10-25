package com.gym.shancai.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.gym.shancai.bean.ForgetPayPwBean;
import com.gym.shancai.http.ForgetPayPwHttp;
import com.gym.shancai.http.base.HttpCallBackListener;


import com.gym.shancai.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 忘记支付密码的设置密码
 */

public class PayPasswordActivity extends BaseActivity {
    @BindView(R.id.etPayPW)
    EditText etPayPW;
    @BindView(R.id.etAffirmPW)
    EditText etAffirmPW;
    @BindView(R.id.btnAffirm)
    Button btnAffirm;
    String tel;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_pay_password;
    }

    @Override
    public String setTitleInitLayout() {
        return "支付密码";
    }

    @OnClick({R.id.btnAffirm})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnAffirm:
                if (TextUtils.isEmpty(etPayPW.getText().toString())) {
                    setErrorToast("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(etAffirmPW.getText().toString())) {
                    setErrorToast("请确认您的密码");
                    return;
                }
                if (!etPayPW.getText().toString().equals(etAffirmPW.getText().toString())) {
                    setErrorToast("您输入的密码不一致");
                    return;
                }
                if (etAffirmPW.getText().toString().length() != 6) {
                    setErrorToast("请输入6位密码");
                    return;
                }


                tel = getIntentStringExtra("type");
                showProgressDialog();
                new ForgetPayPwHttp(tel, etAffirmPW.getText().toString(), new HttpCallBackListener<ForgetPayPwBean>() {
                    @Override
                    public void callBack(int statusCode, ForgetPayPwBean bean, String msg) {
                        setOKToast("密码修改成功！");
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, ForgetPayPwBean bean, String msg) {
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
