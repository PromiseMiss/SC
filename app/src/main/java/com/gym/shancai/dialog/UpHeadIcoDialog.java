package com.gym.shancai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gym.shancai.R;


/**
 * 头像选择dialog
 * @author guoyi
 *
 */
public class UpHeadIcoDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView exit;
    private TextView tv_wx;
    private TextView tv_pay_bao;

    public UpHeadIcoDialog(Context context) {
        this(context, R.style.Theme_Dialog_From_Bottom);
        // this.toChatUsername = ContactsDao.getDisplayUserName(toChatUsername);
    }

    public UpHeadIcoDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init();
    }

    private void init() {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_picway);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;// 让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.BOTTOM;// 出现在底部
        window.setAttributes(lp);

        initViews();
    }

    private void initViews() {
        exit = (TextView) findViewById(R.id.dialog_exit);
        tv_wx = (TextView) findViewById(R.id.wx_pay);
        tv_pay_bao=(TextView) findViewById(R.id.pay_bao);

        exit.setOnClickListener(this);
        tv_wx.setOnClickListener(this);
        tv_pay_bao.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.dialog_exit:
        	selectListener.onSelect(0);
            dismiss();
            break;
        case R.id.wx_pay:
        	selectListener.onSelect(1);
            dismiss();
            break;
        case R.id.pay_bao:
        	selectListener.onSelect(2);
            dismiss();
            break;
        default:
            break;
        }
    }
    public  SelectListener selectListener;
public interface SelectListener{
	/**
	 * 
	 * 0取消
	 * 1拍照
	 * 2相册
	 * @param type
	 * 
	 */
    void onSelect(int type);
	
}
public void setonSelectListener(SelectListener selectListener){
	this.selectListener=selectListener;
}


}