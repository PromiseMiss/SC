package com.gym.shancai.http;

import android.support.annotation.NonNull;

import com.gym.shancai.bean.WxRechargeBean;
import com.gym.shancai.http.base.BaseHttp2;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.http.base.ServerUrl;
import com.gym.shancai.utils.SharedPreferencesSetting;

/**
 * Created by gym on 2017/9/14.
 */

public class WxRechargeHttp extends BaseHttp2{
    /**
     *  @apiParam {String} usersid 用户id
     @apiParam {String} phonetoken 异地登录标志
     @apiParam {String} money 金额
     @apiParam {String} type 类型（1：微信充值 2：微信支付）
     @apiParam {String} orderid 订单id（类型为2时传）
     * @param httpCallBackListener
     */
    public WxRechargeHttp(String  money,int type,String orderid, HttpCallBackListener<WxRechargeBean> httpCallBackListener) {
        put("usersid", SharedPreferencesSetting.getInstance().getUserId());
        put("type",type+"");
        if (type==2) {
            put("orderid", orderid);
        }
        put("money",money);
        this.listener=httpCallBackListener;

    }

    @NonNull
    @Override
    public String getUrl() {
        return ServerUrl.getInstance().getWxRechargeinfo;
//        return "http://www.zhuandj.com/zdjapi/api.php/Home/PayMoney/zdjrechargeOrder";
    }

    @NonNull
    @Override
    public Class getClazz() {
        return WxRechargeBean.class;
    }
}
