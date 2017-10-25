/**
 * 
 */
package com.gym.shancai.utils;


import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.shancai.APP;
import com.gym.shancai.R;


public class ToastUtils {
    static Toast toast;


	public static void show( String info) {
		Toast.makeText(APP.getInstance(), info, Toast.LENGTH_LONG).show();
	}

	public static void show(int info) {
		Toast.makeText(APP.getInstance(), info, Toast.LENGTH_LONG).show();
	}
	
	    
    public static Toast  SetToast(int imageId, Spanned fromHtml, int duration) {
        return init(imageId,null,fromHtml,duration);

    }
    public static Toast SetToast( int imageId ,String content , int duration){
       return init(imageId,content,null,duration);
    }

    /**
     * 初始化并且显示
     */
    private static Toast init(int imageId,String content,Spanned spanned,int duration) {
        if (toast!=null)
            toast.cancel();
        View layout = LayoutInflater.from(APP.getInstance()).inflate(R.layout.base_toast,
                null);
        ImageView image = (ImageView) layout
                .findViewById(R.id.tvImageToast);
        image.setBackgroundResource(imageId);
        TextView text = (TextView) layout.findViewById(R.id.tvTitleToast);
        if (spanned==null) {
            text.setText(content);
        }else{
            text.setText(spanned);
        }
        toast= new Toast(APP.getInstance());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
        return toast;
    }

    /**
     * 哭脸吐司
     * @param content 内容
     */
    public static void setErrorToast(String content){
       SetToast(R.drawable.base_toast_face_sad,content , 1000);
    }
    /**
     * 笑脸吐司
     * @param content 文字
     */
    public static void setOKToast(String content){
        SetToast(R.drawable.base_toast_face_ok,content , 1000);
    }
    /**
     * 哭脸吐司
     * @param content 内容
     */
    public static void setErrorToast(Spanned content){
       SetToast(R.drawable.base_toast_face_sad,content , 1000);
    }
    /**
     * 笑脸吐司
     * @param content 文字
     */
    public static void setOKToast(Spanned content){
        SetToast(R.drawable.base_toast_face_ok,content , 1000);
    }

    /**
     * 取消吐司
     */
    public static void cancelToast(){
        if (toast!=null)
        toast.cancel();
    }
}
