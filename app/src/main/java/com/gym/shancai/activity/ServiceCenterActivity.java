package com.gym.shancai.activity;

import android.view.View;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.ServerPhoneBean;
import com.gym.shancai.http.ServerPhoneHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/26 0026.
 * 客服中心
 */

public class ServiceCenterActivity extends BaseActivity {


    @BindView(R.id.tvOnlineService)
    TextView tvOnlineService;
    @BindView(R.id.tvPhoneService)
    TextView tvPhoneService;
    @BindView(R.id.tvFAQ)
    TextView tvFAQ;
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;


    ServerPhoneBean data;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_service_center;
    }

    @Override
    public String setTitleInitLayout() {
        return "客服中心";
    }


    @OnClick({R.id.head_left_text_button, R.id.tvOnlineService, R.id.tvPhoneService, R.id.tvFAQ})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
            case R.id.tvOnlineService:
                setErrorToast("该功能暂未开放");
                break;
            case R.id.tvPhoneService:

                showProgressDialog();
                new ServerPhoneHttp(new HttpCallBackListener<ServerPhoneBean>() {
                    @Override
                    public void callBack(int statusCode, ServerPhoneBean bean, String msg) {
                        data = bean;
                        CommonUtils.callPhone(ServiceCenterActivity.this, data.getServerphone());
                    }

                    @Override
                    public boolean onUserFail(int statusCode, ServerPhoneBean bean, String msg) {
                        setErrorToast("操作失败，请稍后再试！");
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
            case R.id.tvFAQ:
                CommonUtils.startActivity(this, CommonQuestionsActivity.class);
                break;
        }
    }
}
