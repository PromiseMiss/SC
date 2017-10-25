package com.gym.shancai.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.CheckMessageBean;
import com.gym.shancai.bean.SendMessageBean;
import com.gym.shancai.http.CheckMessageHttp;
import com.gym.shancai.http.SendMessageHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.runable.SmsTimerRunable;
import com.gym.shancai.utils.CommonUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 注册界面 第一步
 */

public class RegisterActivity extends BaseActivity {
    Context context;


    @BindView(R.id.etYourTel)
    EditText etUser;
    @BindView(R.id.etCodeGetting)
    EditText etPass;
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;

    String phone;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    public String setTitleInitLayout() {
        setLeftDrawable(R.drawable.icon_close);
        context = this;
        setRightButtonText("下一步");
        return "善菜网注册";
    }

    @OnClick({ R.id.head_right_text_button})
    public void onClick(View v) {
        switch (v.getId()) {
                       // 下一步 进行验证码对比
            case R.id.head_right_text_button:
                showProgressDialog();
                new CheckMessageHttp(etUser.getText().toString(), etPass.getText().toString()
                        , new HttpCallBackListener<CheckMessageBean>() {
                    @Override
                    public void callBack(int statusCode, CheckMessageBean bean, String msg) {
                        params = new HashMap();
                        params.put("phone",etUser.getText().toString());

                        CommonUtils.startActivity(context, RegisterPasswordActivity.class, params);
                        finish();

                    }

                    @Override
                    public boolean onUserFail(int statusCode, CheckMessageBean bean, String msg) {
                        setErrorToast("验证码错误");
                        return true;
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



    /**
     * 验证码发送相关
     */
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    private Handler handler = new Handler();
    private Runnable runnable;
    public void getCode(final View view) {

        if (etUser.getText().toString().length() != 11) {
            setErrorToast("请输入一个有效的手机号");
            return;
        }
        showProgressDialog("");
//        new CheckPhoneNumHttp(etPhoneNum.getText().toString(), new HttpCallBackListener() {
//            @Override
//            public void callBack(int statusCode, Object bean, String msg) {
//                Logger.e("检查手机号成功");

        String phoneNum = etUser.getText().toString();
        new SendMessageHttp(phoneNum, new HttpCallBackListener<SendMessageBean>() {
            @Override
            public void callBack(int statusCode, SendMessageBean bean, String msg) {
                runnable = new SmsTimerRunable(tvGetCode, handler);
                handler.postDelayed(runnable, 1000);

                setOKToast(getString(R.string.sms_sended));
            }

            @Override
            public boolean onUserFail(int statusCode, SendMessageBean bean, String msg) {
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
