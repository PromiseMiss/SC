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
 * Created by Administrator on 2017/8/25 0025.
 * 忘记密码
 */

public class ForgetPasswordActivity extends BaseActivity {


    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.etRegPhone)
    EditText etRegPhone;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.etCodeGetting)
    EditText etCodeGetting;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;

    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_forget_password;
    }

    @Override
    public String setTitleInitLayout() {
        setLeftDrawable(R.drawable.icon_close);
        setRightButton(R.id.head_right_button);
        setRightButtonText("下一步");
        return "忘记密码";
    }

    @OnClick({R.id.head_left_text_button, R.id.tvGetCode, R.id.head_right_text_button})
    public void OnClick(View view) {
        switch (view.getId()) {

            //点击下一步跳转重置界面
            case R.id.head_right_text_button:
                if (etRegPhone.getText().toString().length()!=11){
                    setErrorToast("请输入一个合法的手机号");
                    return;
                }
                if(isEmp(etCodeGetting.getText().toString())){
                    setErrorToast("请输入验证码");
                    return;
                }
                showProgressDialog();
                new VerifyForgetPWMesHttp(etRegPhone.getText().toString()
                        , etCodeGetting.getText().toString(), new HttpCallBackListener<VerifyForgetPWMesBean>() {
                    @Override
                    public void callBack(int statusCode, VerifyForgetPWMesBean bean, String msg) {
                        params = new HashMap();
                        params.put("type", etRegPhone.getText().toString());
                        CommonUtils.startActivity(MainActivity.getInstence(), ResetPasswordActivity.class, params);
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

            case R.id.head_left_text_button:
                finish();
                break;
            //获取验证码
            case R.id.tvGetCode:

                if (etRegPhone.getText().toString().length() != 11) {
                    setErrorToast("请输入一个有效的手机号");
                    return;
                }


                new ForgetPWMessageHttp(etRegPhone.getText().toString(), new HttpCallBackListener<ForgetPWMessageBean>() {
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

                    }
                }).post();

//                // 验证码倒计时.
//                CountDownUtils.getCountDownTimer()
//                        .setMillisInFuture(60000)
//                        .setCountDownInterval(1000)
//                        .setTickDelegate(new CountDownUtils.TickDelegate() {
//                            @Override
//                            public void onTick(long pMillisUntilFinished) {
//                                tvGetCode.setText(pMillisUntilFinished / 1000 + getString(R.string.second_again_get));
//                                tvGetCode.setClickable(false);
//                            }
//                        }).setFinishDelegate(new CountDownUtils.FinishDelegate() {
//                    @Override
//                    public void onFinish() {
//                        tvGetCode.setText(R.string.again_get);
//                        tvGetCode.setClickable(true);
//                    }
//                }).start();

                break;
        }
    }
}
