package com.gym.shancai.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.gym.shancai.APP;
import com.gym.shancai.bean.LoginBean;

public abstract class BaseSharedPreferences {
    /**
     * 存放基本信息的文件名
     */
    public static final String GENERAL_SETTING = "GeneralSetting";
    /**
     * 版本号
     */
    public static final String VEISION = "Version";
    public static final String VEISION_CODE = "VersionCode";

    public static final String LOGIN_INFO = "UserInfo";
    public static final String REMAND_PHONE="RemandPhone";

    public LoginBean loginBean;

    private static BaseSharedPreferences instance = null;

    public static BaseSharedPreferences getInstance() {
        if (instance == null) {
            instance = new BaseSharedPreferences() {

                @Override
                public String getFilename() {
                    // TODO Auto-generated method stub
                    return GENERAL_SETTING;
                }
            };
        }
        return instance;
    }

    public abstract String getFilename();

    public void set(String key, String value) {
        set(key, value, getFilename());
    }

    public void set(String key, String value, String filename) {
        if (key == null)
            return;

        if (value == null) {
            value = "";
        }

        SharedPreferences pref = APP.getInstance().getSharedPreferences(
                filename, android.content.Context.MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key) {
        return get(key, getFilename());
    }

    public String get(String key, String filename) {
        if (key == null)
            return "";
        SharedPreferences pref = APP.getInstance().getSharedPreferences(
                filename, android.content.Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public void setVersion(String value, int code) {
        set(VEISION, value, GENERAL_SETTING);
        setVersionCode(code);
    }
    public void setRemandPhone(String value){

        set(REMAND_PHONE,value,GENERAL_SETTING);
    }
    public String getRemandPhone(){
        return get(REMAND_PHONE);
    }
    public String getVersion() {
        return get(VEISION, GENERAL_SETTING);
    }

    public int getVersionCode() {
        String value=get(VEISION_CODE, GENERAL_SETTING);
        if (TextUtils.isEmpty(value)){
            return 0;
        }
        return Integer.parseInt(value);
    }

    public void setVersionCode(int code) {
        set(VEISION_CODE, code + "", GENERAL_SETTING);
    }

    public String getVersionCodeString() {
        return get(VEISION_CODE, GENERAL_SETTING);
    }

    /**
     * 设置用户登录信息
     *
     * @param bean
     */
    public void setLoginInfo(LoginBean bean) {
        set(LOGIN_INFO, GsonUtils.getInstance().toJson(bean), GENERAL_SETTING);
        loginBean = bean;
    }

    /**
     * 获取用户登录信息
     *
     * @return
     */
    public LoginBean getLoginInfo() {
//        ||TextUtils.isEmpty(loginBean.getSupplierid())
            try {
                loginBean = GsonUtils.getInstance().fromJson(get(LOGIN_INFO, GENERAL_SETTING), LoginBean.class);
            } catch (Exception e) {
                Logger.e("~~~~~~~","~~~~~~~~+提取信息错误");
            }
        if (loginBean == null) {
            loginBean = new LoginBean();
        }
        return loginBean;
    }

    /**
     * 清空用户登录信息
     */
    public void clearnLoginInfo() {
        loginBean = null;
        set(LOGIN_INFO, "", GENERAL_SETTING);
    }

    /**
     * 用户是否登录状态
     *
     * @return
     */
    public boolean isLogin() {
        return getLoginInfo().getUserid() != null;
    }

    public String getUserId() {
        if (getLoginInfo() == null) {
            return "";
        }
        return getLoginInfo().getUserid();
    }

//    public String getPhoneNum() {
//        if (getLoginInfo() == null) {
//            return "";
//        }
//        return getLoginInfo().getUserphone();
//    }
}
