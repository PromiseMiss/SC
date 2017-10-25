package com.gym.shancai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.Constant;
import com.gym.shancai.R;
import com.gym.shancai.customview.PwdEditText;
import com.gym.shancai.utils.CommonUtils;


public class PayDialog extends Dialog implements View.OnClickListener {
    private PwdEditText pwdEditText;

    public Float getMoney() {
        return money;
    }

    private Float money;
    private Context context;
    private ImageView iv_close;
    private String payWayString;

    private OnDialogPasswordFinishListener listener;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_rl_share_close:
                dismiss();
            break;
            case R.id.tv_first:
                listener.onFirstSet();
//                CommonUtils.startActivity(context,NewMyWalletActivity.class);
                break;
        }

    }

    /**
     * 密码输入结束监听
     *
     * @param
     */
    public interface OnDialogPasswordFinishListener {
        void onInputFinish(String password);
        void onFirstSet();
        // void passWordRight();
        //
        // void passWordWrong();
    }

    public PayDialog(Context context, Float money, int payWay, OnDialogPasswordFinishListener listener) {
        super(context, R.style.OneClickBuyDeleteDialog);
        this.listener = listener;
        this.money = money;
        this.context = context;
        switch (payWay){
            case Constant.PAYWAY_YUE:
                payWayString="余额支付";
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        getWindow().setWindowAnimations(R.style.dialog_anim);
        TextView qian = (TextView) findViewById(R.id.qian);
        TextView firstSet= (TextView) findViewById(R.id.tv_first);
        TextView tvPayWay= (TextView) findViewById(R.id.tv_payway);
        iv_close= (ImageView) findViewById(R.id.iv_rl_share_close);

        tvPayWay.setText(payWayString);
        iv_close.setOnClickListener(this);
        firstSet.setOnClickListener(this);
        qian.setText("￥"+money );

        pwdEditText = (PwdEditText) findViewById(R.id.pet_pwd_sb);
        pwdEditText.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {

                if (password.length() == 6) {
                    dismiss();
                    listener.onInputFinish(password);

                }
            }
        });

    }
}
