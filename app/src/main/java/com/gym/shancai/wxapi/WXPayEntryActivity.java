package com.gym.shancai.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.activity.BaseActivity;
import com.gym.shancai.utils.Logger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建:gym
 * 日期:2016-11-14.
 * 说明:
 * 备注:
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvBackWallet)
    TextView tvBackWallet;
    @BindView(R.id.iv)
    ImageView iv;


    /**
     * return一个布局文件 用来设置当前的activity
     *
     * @return
     */
    @Override
    public int returnLayoutResID() {
        return R.layout.activity_receipt_ok;
    }

    /**
     * 设置一个标题
     *
     * @return
     */
    @Override
    public String setTitleInitLayout() {
        api = WXAPIFactory.createWXAPI(this, "wxe5537aa41983c439");
        api.handleIntent(getIntent(), this);
        return "支付结果";
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
//        if(resp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
//        Logger.e("支付", "onPayFinish,errCode=" + resp.errCode + "支付结果" + resp.errStr);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("支付结果" + resp.errCode);
        switch (resp.errCode){
            case 0:
                iv.setVisibility(View.VISIBLE);
                tvContent.setText("支付成功");
                break;
            case -1:
                iv.setVisibility(View.GONE);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("支付结果" + resp.errCode);
                tvContent.setText("支付失败");
                break;
            case -2:
                finish();
                break;
        }

//        builder.show();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvContent, R.id.tvBackWallet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvContent:
                break;
            case R.id.tvBackWallet:
                finish();
                break;
        }
    }
}
