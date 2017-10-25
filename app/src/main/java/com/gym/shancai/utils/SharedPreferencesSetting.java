package com.gym.shancai.utils;


/**
 * 
 * @author util
 * 
 * */

public class SharedPreferencesSetting extends BaseSharedPreferences {
    public static SharedPreferencesSetting instance = null;
    public static final String DATA_DISCOUNTREDPACK = "Discount";

    @Override
    public String getFilename() {
        return "setting_file_" + getUserId();
    }

    public static SharedPreferencesSetting getInstance() {
        if (instance == null)
            instance = new SharedPreferencesSetting();

        return instance;
    }



}
