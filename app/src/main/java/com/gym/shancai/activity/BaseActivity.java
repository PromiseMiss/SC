package com.gym.shancai.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.dialog.BaseLoadingDialog;
import com.gym.shancai.utils.DialogUtils;
import com.gym.shancai.utils.ToastUtils;

import java.util.HashMap;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
    private TextView tvTitle;
    private boolean toastAutoCancel = true;
    public HashMap params;

    /**
     * 当activity pause时候  toast是否自动取消
     * @param toastAutoCancel
     */
    public void setToastAutoCancel(boolean toastAutoCancel) {
        this.toastAutoCancel = toastAutoCancel;
    }

    /**
     * 在oncreate后调用 修改标题
     * @param title
     */
    public void setTitle(String title){
        tvTitle.setText(title);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//        CommonUtils.changeStateBarColor(getWindow(), Color.WHITE);
        setContentView(returnLayoutResID());
        ButterKnife.bind(this);
        tvTitle = (TextView) findViewById(R.id.head_title);
        if (tvTitle != null) {
            setTitle(setTitleInitLayout());
        }else {
            setTitleInitLayout();
        }
        initView();
    }

    /**
     * 为了兼容老框架的initVIew 方法
     */
    public void initView(){

    }

    /**
     * return一个布局文件 用来设置当前的activity
     *
     * @return
     */
    public abstract int returnLayoutResID();

    /**
     * 设置一个标题
     *
     * @return
     */
    public abstract String setTitleInitLayout();

    /**
     * 获取标题String
     */
    public String getTitleString() {
        return tvTitle.getText().toString();
    }

    /**
     * activity销毁方法
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }


    public void errorDialog(String msg) {
        DialogUtils.setErrorDialog(this, msg);
    }

    public void errorToast(String msg) {
        ToastUtils.setErrorToast(msg);
    }

    public void okToast(String msg) {
        ToastUtils.setOKToast(msg);
    }

    public void setRightButton(int Resid) {
        ImageView rightButton = (ImageView) findViewById(R.id.head_right_button);
        rightButton.setImageResource(Resid);
        rightButton.setVisibility(View.VISIBLE);
    }

    public void setRightButtonText(String s) {
        TextView rightButton = (TextView) findViewById(R.id.head_right_text_button);
        rightButton.setText(s);
        rightButton.setVisibility(View.VISIBLE);
    }

    private BaseLoadingDialog progressDialog;
    public BaseLoadingDialog showProgressDialog(){
        return showProgressDialog("");
    }
    public BaseLoadingDialog showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new BaseLoadingDialog(this).setContent(content);
        }
            progressDialog.setContent(content);

        progressDialog.setCancelable(true);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 哭脸吐司
     * @param content 内容
     */
    public static void setErrorToast(String content) {
        ToastUtils.SetToast(R.drawable.base_toast_face_sad, content, 1000);
    }

    /**
     * 笑脸吐司
     * @param content 文字
     */
    public static void setOKToast( String content) {
        ToastUtils.SetToast(R.drawable.base_toast_face_ok, content, 1000);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (toastAutoCancel)
            ToastUtils.cancelToast();

//        JPushInterface.onPause(APP.getInstance());
    }
    public void setTitleLineColor(int color){
        View view=findViewById(R.id.view_title_line);
        view.setBackgroundColor(color);
    }
    public void rightClick(View v) {

    }
    public boolean isEmp(CharSequence charSequence){
        return TextUtils.isEmpty(charSequence);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
    public void setLeftDrawable(int resid){
        TextView leftButton = (TextView) findViewById(R.id.head_left_text_button);
        Drawable leftDrawable = ContextCompat.getDrawable(this,resid);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        leftButton.setCompoundDrawables(leftDrawable, null, null, null);
    }
    public void setLeftDrawableAndTextColor(int resid,int color){
        TextView leftButton = (TextView) findViewById(R.id.head_left_text_button);
        leftButton.setTextColor(color);
        leftButton.setTextColor(color);
        Drawable leftDrawable = ContextCompat.getDrawable(this,resid);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        leftButton.setCompoundDrawables(leftDrawable, null, null, null);
    }
    public String getIntentStringExtra(String key){
        String result=this.getIntent().getStringExtra(key);
        if (result==null){
            throw new NullPointerException("参数空指针,请检查传参");
        }
        return result;
    }

}
