package com.gym.shancai.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.http.CheckPassHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 修改密码-hjd
 */

public class ChangeLoginPWActivity extends BaseActivity {


    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.etFormerPW)
    EditText etFormerPW;
    @BindView(R.id.etNewPW)
    EditText etNewPW;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.tvForgetPW)
    TextView tvForgetPW;

    private BaseDialog dialog;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_change_login_password;
    }

    @Override
    public String setTitleInitLayout() {
        dialog = new BaseDialog(this);
        return "修改登录密码";
    }

    @OnClick({R.id.btnReset, R.id.tvForgetPW})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btnReset:
                showProgressDialog();
                new CheckPassHttp(etFormerPW.getText().toString(), etNewPW.getText().toString(), new HttpCallBackListener() {
                    @Override
                    public void callBack(int statusCode, Object bean, String msg) {
//                        dialog.addCancel();
                        dialog.addOk(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                                MainActivity.getInstence().logout();
                                dialog.dismiss();
                            }
                        });
                        dialog.setContent("修改成功，请您重新登陆");
                        dialog.show();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, Object bean, String msg) {
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
                CommonUtils.startActivity(this, ForgetPasswordActivity.class);
                break;
        }
    }
}
