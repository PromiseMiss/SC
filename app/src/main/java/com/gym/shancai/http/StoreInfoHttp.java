package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.SelfInfoBean;
import com.gym.shancai.bean.StoreInfoBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.ImageUtils;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class StoreInfoHttp extends BaseHttp2 {

    public StoreInfoHttp(String userid, String storename, String tel, String licenceid, String areaid
    , String reallyname, String idcard,String licencepicUrl,String detailarea,   HttpCallBackListener<SelfInfoBean> listener) {
        this.listener = listener;
        put("userid", userid);
        put("storename", storename);
        put("tel", tel);
        put("licenceid", licenceid);
        put("areaid", areaid);
        put("reallyname", reallyname);
        put("idcard", idcard);
        put("detailarea",detailarea);
        put("licencepic", ImageUtils.INSTANCE.getBitmapBase64(licencepicUrl));
    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().storeInfo;
    }

    @NonNull
    @Override
    public Class getClazz() {
        return StoreInfoBean.class;
    }
}
