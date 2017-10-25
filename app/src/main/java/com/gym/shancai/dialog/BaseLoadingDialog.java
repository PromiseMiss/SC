package com.gym.shancai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.customview.BaseLoadingView;


public class BaseLoadingDialog extends Dialog{
    BaseLoadingView loadingView;
    private Context context;
    private String content;
    private boolean canCancel=true;
    TextView tvContent;

    public BaseLoadingDialog(Context context) {
        super(context,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.base_progress, null);
        addContentView(layout, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().getAttributes().dimAmount = 0f;
        setCanceledOnTouchOutside(false);
        setCancelable(canCancel);
        tvContent = (TextView) layout.findViewById(R.id.dialog_progress_tv);
        loadingView = (BaseLoadingView) findViewById(R.id.loading);
        if (TextUtils.isEmpty(content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setText(content);
        }
        setContentView(layout);
    }

    public BaseLoadingDialog setContent(String content) {
        this.content=content;
        if (TextUtils.isEmpty(content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setText(content);
            tvContent.setVisibility(View.VISIBLE);
        }
        return this;
    }

    @Override
    public void show() {
        loadingView.startRotationAnimation();
        super.show();
    }

    @Override
    public void dismiss() {
        if (isShowing()){
        super.dismiss();
        loadingView.stopRotationAnimation();}
    }
}
