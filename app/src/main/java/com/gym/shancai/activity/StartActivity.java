package com.gym.shancai.activity;

import android.content.res.Configuration;
import android.view.View;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.dialog.BottomListDialog;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.CommonUtils;

import static com.gym.shancai.http.base.ServerUrl.baseUrl;
import static com.gym.shancai.http.base.ServerUrl.debugUrl;

public class StartActivity extends BaseActivity {


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_start;
    }

    @Override
    public String setTitleInitLayout() {
        if (!APP.EXTERNAL_RELEASE) {
            //测试版本
            final BottomListDialog dialog = new BottomListDialog(this);
            dialog.addItem("正式环境", baseUrl);
//            dialog.addItem("1.102测试服务器",debug2Url);
            for (int i=0;i<debugUrl.length;i++){
            dialog.addItem("1.160测试服务器", debugUrl[i]);}
            dialog.goneCancel();
            dialog.setonSelectListener(new BottomListDialog.SelectListener() {
                @Override
                public void onSelect(int position, View view, Object tag) {
                    ServerUrl.ServerUrl = (dialog.getTag(position).toString());
                    goOn();
                }
            });
            dialog.show();
        } else {
            CommonUtils.startActivity(StartActivity.this, MainActivity.class);
            finish();
            ServerUrl.ServerUrl = (baseUrl);
        }
        return null;
    }

    private void goOn() {
        CommonUtils.startActivity(this, MainActivity.class);
        finish();
    }

}
