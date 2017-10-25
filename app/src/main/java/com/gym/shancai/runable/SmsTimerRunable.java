package com.gym.shancai.runable;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;


import com.gym.shancai.R;

import static com.gym.shancai.APP.appContext;


/**
 * 创建:gym
 * 日期:2017-02-24.
 * 说明:
 * 备注:短信验证码 倒计时类
 */

public class SmsTimerRunable implements Runnable {
private TextView textView;
    private Handler handler;
    int i;
    /**
     * 验证码的等待时间
     */
    public static final int waitTime=60;
    public SmsTimerRunable(View textView , Handler handler) {
        if (!(textView instanceof TextView)){
           throw  new IllegalArgumentException("必须是继承TextView的View");
        }
       this.textView = (TextView) textView;
        this.handler=handler;
        i = 0;
        textView.setClickable(false);
        this.textView.setBackgroundColor(ContextCompat.getColor(appContext , R.color.sms_bg_color));
        this.textView.setTextColor(ContextCompat.getColor(appContext,R.color.sms_text_color));
        this.textView.setText(waitTime + appContext.getString(R.string.sms_sedsend));
    }

    @Override
    public void run() {
        if(i <= waitTime){
            int waitTimeDate = waitTime - i;
            textView.setText(waitTimeDate + appContext.getString(R.string.sms_sedsend));
            if (waitTimeDate == 0) {
                textView.setText(R.string.sms_resend);
                textView.setBackgroundColor(ContextCompat.getColor(appContext,R.color.sms_bg_color_));
                textView.setTextColor(ContextCompat.getColor(appContext,R.color.sms_text_color_));
                textView.setClickable(true);

            } else {

                handler.postDelayed(this, 1000);
                i++;
            }
        }
    }
}
