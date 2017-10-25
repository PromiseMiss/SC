package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class MyWalletTopUpBean {

    /**
     * phone : 19283838383
     * activity : [{"id":"3","money":"500.00","sendmoney":"50.00"},{"id":"4","money":"1000.00","sendmoney":"120.00"}]
     */

    private String phone;
    private List<ActivityBean> activity;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ActivityBean> getActivity() {
        return activity;
    }

    public void setActivity(List<ActivityBean> activity) {
        this.activity = activity;
    }

    public static class ActivityBean {
        /**
         * id : 3
         * money : 500.00
         * sendmoney : 50.00
         */

        private String id;
        private String money;
        private String sendmoney;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getSendmoney() {
            return sendmoney;
        }

        public void setSendmoney(String sendmoney) {
            this.sendmoney = sendmoney;
        }
    }
}
