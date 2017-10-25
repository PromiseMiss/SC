package com.gym.shancai.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gym on 2017/9/14.
 */

public class WxRechargeBean {

    /**
     * appid : wxe5537aa41983c439
     * noncestr : OjgcTAQNMdx6XhyXnoCIvDWdkBfNinLi
     * package : Sign=WXPay
     * partnerid : 1486663442
     * prepayid : wx20170918154424d7fa52387f0878656301
     * timestamp : 1505720663
     * sign : 39D7786A2176F7BC1FF0CD63AF268F51
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private int timestamp;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
