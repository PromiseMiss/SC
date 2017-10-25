package com.gym.shancai.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.EditPayPasswordBean;
import com.gym.shancai.http.EditPayPasswordHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.WorksSizeCheckUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 修改支付密码-hjd
 */

public class AlterPayPasswordActivity extends BaseActivity {


    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;

    @BindView(R.id.btnAffirm)
    Button btnAffirm;
    @BindView(R.id.tvForgetPW)
    TextView tvForgetPW;
    @BindView(R.id.etOldPW)
    EditText etOldPW;
    @BindView(R.id.etNewPW)
    EditText etNewPW;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_alter_pay_password;
    }

    @Override
    public String setTitleInitLayout() {
//        requestData();
        return "修改支付密码";
    }

    @OnClick({R.id.btnAffirm, R.id.tvForgetPW})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btnAffirm:

                if (TextUtils.isEmpty(etOldPW.getText().toString())) {
                    setErrorToast("请输入原密码");
                    return;
                }
                if (TextUtils.isEmpty(etNewPW.getText().toString())) {
                    setErrorToast("请输入新密码");
                    return;
                }
                if (etNewPW.getText().toString().length() != 6) {
                    setErrorToast("请输入6位密码");
                    return;
                }
                showProgressDialog();
                new EditPayPasswordHttp(etOldPW.getText().toString(), etNewPW.getText().toString(), new HttpCallBackListener<EditPayPasswordBean>() {
                    @Override
                    public void callBack(int statusCode, EditPayPasswordBean bean, String msg) {
                        setOKToast("密码修改成功！");
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, EditPayPasswordBean bean, String msg) {
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
            case R.id.tvForgetPW:
                CommonUtils.startActivity(MainActivity.getInstence(), ForgetPayPasswordActivity.class);
                finish();
                break;
        }
    }
}
