package com.gym.shancai.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.LoginBean;
import com.gym.shancai.http.LoginHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.BaseSharedPreferences;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.MD5Encode;


import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 登录
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.tvForgetPw)
    TextView tvForgetPw;
    @BindView(R.id.etYourAccount)
    EditText etYourAccount;
    @BindView(R.id.etLoginPassWord)
    EditText etLoginPassWord;
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_login;

    }

    @Override
    public String setTitleInitLayout() {
        setLeftDrawable(R.drawable.icon_close);
        return "善菜网登陆";
    }

    @OnClick({R.id.head_left_text_button, R.id.btnLogin, R.id.tvRegister, R.id.tvForgetPw})
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
            case R.id.btnLogin:
                if (TextUtils.isEmpty(etYourAccount.getText().toString())) {
                    setErrorToast("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(etLoginPassWord.getText().toString())) {
                    setErrorToast("请输入密码");
                    return;
                }


                showProgressDialog();
                v.setClickable(false);
                String phonetoke = MD5Encode.GetMD5Code(System.currentTimeMillis() + android.os.Build.MODEL);
                new LoginHttp(etYourAccount.getText().toString(), etLoginPassWord.getText().toString(), phonetoke, new HttpCallBackListener<LoginBean>() {
                    @Override
                    public void callBack(int statusCode, LoginBean bean, String msg) {
                        v.setClickable(true);
                        setOKToast("登陆成功");
                        BaseSharedPreferences.getInstance().setLoginInfo(bean);
                        BaseSharedPreferences.getInstance().setRemandPhone(etYourAccount.getText().toString());
                        /**
                         * (Context context,
                         String alias,
                         Set<String> tags,
                         TagAliasCallback callback)
                         */

//                        JPushInterface.resumePush(APP.appContext);
//                        JPushInterface.setAliasAndTags(APP.getInstance(), bean.getPhonetoken(),null,new TagAliasCallback() {
//
//                            @Override
//                            public void gotResult(int arg0, String arg1, Set<String> arg2) {
//                                // TODO Auto-generated method stub
//                                Logger.e("极光推送登录",""+arg0+"~~~"+arg1);
//                            }
//                        });
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, LoginBean bean, String msg) {
                        return false;
                    }

                    @Override
                    public boolean onNetError(int statusCode, String msg) {
                        return false;
                    }

                    @Override
                    public void onComplete(boolean isError) {
                        if (isError) {
                            v.setClickable(true);
                        }
                        dismissProgressDialog();
                    }
                }).post();
                break;

            case R.id.tvRegister:
                CommonUtils.startActivity(this, RegisterActivity.class);
                break;
            case R.id.tvForgetPw:
                CommonUtils.startActivity(this, ForgetPasswordActivity.class);
                break;
        }
    }


}
