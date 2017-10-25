package com.gym.shancai.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.ForgetPWMessageBean;
import com.gym.shancai.bean.VerifyForgetPWMesBean;
import com.gym.shancai.http.ForgetPWMessageHttp;
import com.gym.shancai.http.VerifyForgetPWMesHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.runable.SmsTimerRunable;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.CountDownUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13 0013.
 * 忘记支付密码
 */

public class ForgetPayPasswordActivity extends BaseActivity {


    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.etCode)
    EditText etCode;
    private Handler handler = new Handler();
    private Runnable runnable;



    @Override
    public int returnLayoutResID() {
        return R.layout.activity_forget_pay_password;
    }

    @Override
    public String setTitleInitLayout() {
        setRightButtonText("下一步");
        return "忘记支付密码";
    }


    @OnClick({R.id.head_right_text_button, R.id.tvGetCode})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.head_right_text_button:
                if (TextUtils.isEmpty(etCode.getText().toString())) {
                    setErrorToast("请补全信息");
                    return;
                }

                showProgressDialog();
                new VerifyForgetPWMesHttp(etTel.getText().toString(), etCode.getText().toString()
                        , new HttpCallBackListener<VerifyForgetPWMesBean>() {
                    @Override
                    public void callBack(int statusCode, VerifyForgetPWMesBean bean, String msg) {
                        params = new HashMap();
                        params.put("type", etTel.getText().toString());
                        CommonUtils.startActivity(MainActivity.getInstence(), PayPasswordActivity.class, params);
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, VerifyForgetPWMesBean bean, String msg) {
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
            case R.id.tvGetCode:
                if (etTel.getText().toString().length() != 11) {
                    setErrorToast("请输入一个有效的手机号");
                    return;
                }
                showProgressDialog();
                new ForgetPWMessageHttp(etTel.getText().toString(), new HttpCallBackListener<ForgetPWMessageBean>() {
                    @Override
                    public void callBack(int statusCode, ForgetPWMessageBean bean, String msg) {
                        runnable = new SmsTimerRunable(tvGetCode, handler);
                        handler.postDelayed(runnable, 1000);

                        setOKToast(getString(R.string.sms_sended));

                    }

                    @Override
                    public boolean onUserFail(int statusCode, ForgetPWMessageBean bean, String msg) {
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
