package com.gym.shancai.http.base;

import android.support.annotation.NonNull;

import com.gym.shancai.APP;
import com.gym.shancai.utils.SharedPreferencesSetting;
import com.gym.shancai.utils.ToastUtils;
import com.gym.shancai.utils.den.PostCall;

import java.util.HashMap;

/**
 * Created by gym on 2017/4/13.
 */

public abstract class BaseHttp2 {
    public HttpCallBackListener listener;
    public HashMap<String ,String > params=new HashMap<>();


    public  <T> void post() {
        Class<T> clazz= getClazz();
        PostCall.Companion.post(getUrl(),params, new PostCall.PostResponse<T>() {

            @Override
            public void onSuccess(int statusCode, ResponseBean responseBeanBody, T responseBean, String msg) {
                if (listener != null) {
                    if (statusCode>0){
                        listener.callBack(statusCode, responseBean,msg);
                    listener.onComplete(false);}
                    else{

                        if (!listener.onUserFail(statusCode,responseBean,msg)){
//                            ToastUtils.setErrorToast(msg+"错误:"+statusCode);
                            if (APP.getInstance().EXTERNAL_RELEASE){
                                ToastUtils.setErrorToast(msg);
                            }else {
                                ToastUtils.setErrorToast(msg+"错误:"+statusCode);
                            }
                        }
                        listener.onComplete(true);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, ResponseBean responseBeanBody, String msg) {
                if (listener != null) {

                    if (!listener.onNetError(statusCode,msg)){
                        ToastUtils.setErrorToast(msg);
                    }
                    listener.onComplete(true);
                }
            }
        }, clazz);

    }
    public void put(String key,String  value){
        params.put(key,value);
    }
    public void putUserId(){
        params.put("userid", SharedPreferencesSetting.getInstance().getUserId());
    }

    @NonNull
    public abstract String getUrl();

    @NonNull
    public abstract Class getClazz();
}
