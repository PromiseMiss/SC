package com.gym.shancai.bean;

/**
 * Created by Administrator on 2017/9/2 0002.
 * 假数据——暂时，会用的到
 */

public class ShopOrderBean {
    public static final int TEXT = 1;
    public static final int IMAGE = 2;

    private int serial;
    private String obligation;
    private int imageSource;
    private String shopName;
    private String detail;
    private String shopMoney;
    private String shopNum;
    private int shopMoneyNum;
    private int freight;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ShopOrderBean(int serial, String obligation, int imageSource, String shopName, String detail
            , String shopMoney, String shopNum, int shopMoneyNum, int freight) {
        this.serial = serial;
        this.obligation = obligation;
        this.imageSource = imageSource;
        this.shopName = shopName;
        this.detail = detail;
        this.shopMoney = shopMoney;
        this.shopNum = shopNum;
        this.shopMoneyNum = shopMoneyNum;
        this.freight = freight;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getObligation() {
        return obligation;
    }

    public void setObligation(String obligation) {
        this.obligation = obligation;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getShopMoney() {
        return shopMoney;
    }

    public void setShopMoney(String shopMoney) {
        this.shopMoney = shopMoney;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public int getShopMoneyNum() {
        return shopMoneyNum;
    }

    public void setShopMoneyNum(int shopMoneyNum) {
        this.shopMoneyNum = shopMoneyNum;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }
}
