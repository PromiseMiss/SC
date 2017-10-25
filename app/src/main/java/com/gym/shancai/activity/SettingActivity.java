package com.gym.shancai.activity;

import android.view.View;
import android.widget.TextView;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tvLoginPW)
    TextView tvLoginPW;
    @BindView(R.id.tvPayPassword)
    TextView tvPayPassword;
    @BindView(R.id.tvPhoneNum)
    TextView tvPhoneNum;
    @BindView(R.id.tvManage)
    TextView tvManage;
    @BindView(R.id.tvClearCache)
    TextView tvClearCache;

    private BaseDialog dialog;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    public String setTitleInitLayout() {
        dialog = new BaseDialog(this);
        return "设置";
    }

    @OnClick({R.id.btExitLogin, R.id.tvLoginPW, R.id.tvPayPassword, R.id.tvPhoneNum, R.id.tvManage, R.id.tvClearCache})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLoginPW:
                CommonUtils.startActivity(this, ChangeLoginPWActivity.class);
                finish();
                break;
            case R.id.tvPayPassword:
                CommonUtils.startActivity(this, AlterPayPasswordActivity.class);
                break;
            case R.id.tvPhoneNum:

//                CommonUtils.startActivity(this, SetPhoneNumActivity.class);
                dialog.addOk();
                dialog.setContent("暂时不能修改手机号");
                dialog.show();
                break;
            case R.id.tvManage:
                CommonUtils.startActivity(this, AddressListActivity.class);
                break;
            case R.id.tvClearCache:
                break;
            case R.id.btExitLogin:

                dialog.addCancel();
                dialog.addOk(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
//                        BaseSharedPreferences.instance.clearnLoginInfo();
//                        JPushInterface.stopPush(APP.appContext);

//                        CommonUtils.startActivity(getActivity(), LoginActivity.class);
                        finish();
                        dialog.dismiss();

                        MainActivity.getInstence().logout();
                    }
                });
                dialog.setContent("确定要退出登录吗？");
                dialog.show();
                break;
        }
    }
}
