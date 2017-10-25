package com.gym.shancai;

import android.app.Application;
import android.content.Context;

//import com.gym.shancai.activity.BuildConfig;
import com.gym.shancai.bean.LoginBean;
import com.gym.shancai.utils.CrashHandler;
import com.gym.shancai.utils.SharedPreferencesSetting;
//import com.squareup.leakcanary.LeakCanary;

public class APP extends Application {
    public static Context appContext;
    public static APP instance;
    String serverUrl;
    //是否为release版
    public static final boolean EXTERNAL_RELEASE = BuildConfig.ENVIRONMENT;

    public boolean isviewsalesvolume() {
        return isviewsalesvolume;
    }

    public void setIsviewsalesvolume(boolean isviewsalesvolume) {
        this.isviewsalesvolume = isviewsalesvolume;
    }

    public static void setInstance(APP instance) {
        APP.instance = instance;
    }

    /**
     * 是否显示销量数据
     */
    private boolean isviewsalesvolume = false;

    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);

        appContext = this;
        instance = this;
        //日志服务
        if (EXTERNAL_RELEASE) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this);
        }
        // GetServerTimeHttp.getData2SP();
        // 推送服务初始化
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        JPushInterface.requestPermission(this);
////        BaseSharedPreferences.getInstance().setUserId("");
//        // Umeng
//
//
//        PlatformConfig.setWeixin("wx6dba85bdababca4d", "c34b576f85d64cd3530d6031f4543a85");
//        // 微信 appid appsecret
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
//        // 新浪微博 appkey appsecret
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        // QQ和Qzone appid appkey
//        PlatformConfig.setAlipay("2015111700822536");
//        // 支付宝 appid
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        // 易信 appkey
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        // Twitter appid appkey
//        PlatformConfig.setPinterest("1439206");
//        // Pinterest appid
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        // 来往 appid appkey

    }

    public static APP getInstance() {
        return instance;
    }

    public LoginBean getLoginBean() {
        if (isLogin()) {
            return SharedPreferencesSetting.getInstance().getLoginInfo();
        } else {
            return new LoginBean();
        }
    }

    public static boolean isLogin() {
        // TODO Auto-generated method stub
        return SharedPreferencesSetting.getInstance().isLogin();
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }
}
