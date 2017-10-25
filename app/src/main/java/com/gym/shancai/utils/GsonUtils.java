package com.gym.shancai.utils;

import com.google.gson.Gson;

/**
 * Created by gym on 2017/4/15.
 */

public class GsonUtils {
    public static Gson instance;

    public static Gson getInstance() {
        if (instance==null)
        {
            instance=new Gson();
        }
        return instance;
    }


}
