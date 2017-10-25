package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by gym on 2017/9/14.
 */

public class ConfigOrderBean {

    /**
     * address : {"receivename":"李四","phonenum":"18765467865","addressinfo":"辽宁省沈阳市浑南新区富贵路5号"}
     * givemoney : 0
     * goodsmessage : [{"goodsid":"15","prodname":"小明","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg","price":"23.2","num":"2"},{"goodsid":"16","prodname":"小红","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg","price":"11.2","num":"1"}]
     * postprice : 0.0000
     * sendprice : 200.0000
     * totalmoney : 37.6
     * couponmoney : {"couponid":"1","couponmoney":"20.0000"}
     */

    private AddressBean address;
    private String givemoney;
    private String postprice;
    private String sendprice;
    private double totalmoney;
    private CouponmoneyBean couponmoney;
    private List<GoodsmessageBean> goodsmessage;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getGivemoney() {
        return givemoney;
    }

    public void setGivemoney(String givemoney) {
        this.givemoney = givemoney;
    }

    public String getPostprice() {
        return postprice;
    }

    public void setPostprice(String postprice) {
        this.postprice = postprice;
    }

    public String getSendprice() {
        return sendprice;
    }

    public void setSendprice(String sendprice) {
        this.sendprice = sendprice;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public CouponmoneyBean getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(CouponmoneyBean couponmoney) {
        this.couponmoney = couponmoney;
    }

    public List<GoodsmessageBean> getGoodsmessage() {
        return goodsmessage;
    }

    public void setGoodsmessage(List<GoodsmessageBean> goodsmessage) {
        this.goodsmessage = goodsmessage;
    }

    public static class AddressBean {
        /**
         * receivename : 李四
         * phonenum : 18765467865
         * addressinfo : 辽宁省沈阳市浑南新区富贵路5号
         */

        private String receivename;
        private String phonenum;
        private String addressinfo;

        public String getReceivename() {
            return receivename;
        }

        public void setReceivename(String receivename) {
            this.receivename = receivename;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(String addressinfo) {
            this.addressinfo = addressinfo;
        }
    }

    public static class CouponmoneyBean {
        /**
         * couponid : 1
         * couponmoney : 20.0000
         */

        private String couponid;
        private String couponmoney;

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public String getCouponmoney() {
            return couponmoney;
        }

        public void setCouponmoney(String couponmoney) {
            this.couponmoney = couponmoney;
        }
    }

    public static class GoodsmessageBean {
        /**
         * goodsid : 15
         * prodname : 小明
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg
         * price : 23.2
         * num : 2
         */

        private String goodsid;
        private String prodname;
        private String thumbnail;
        private String price;
        private String num;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
        }

        public String getProdname() {
            return prodname;
        }

        public void setProdname(String prodname) {
            this.prodname = prodname;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
