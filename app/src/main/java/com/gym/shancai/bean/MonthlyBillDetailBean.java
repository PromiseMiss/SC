package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 账单明细月账单
 */

public class MonthlyBillDetailBean {


    /**
     * total : {"month":"2017-08","money":176.6}
     * result : [{"ordercreatedate":"2017-08-14","totalprice":"125.0000"},{"ordercreatedate":"2017-08-14","totalprice":"51.6000"}]
     */

    private TotalBean total;
    private List<ResultBean> result;

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class TotalBean {
        /**
         * month : 2017-08
         * money : 176.6
         */

        private String month;
        private double money;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }

    public static class ResultBean {
        /**
         * ordercreatedate : 2017-08-14
         * totalprice : 125.0000
         */

        private String ordercreatedate;
        private String totalprice;

        public String getOrdercreatedate() {
            return ordercreatedate;
        }

        public void setOrdercreatedate(String ordercreatedate) {
            this.ordercreatedate = ordercreatedate;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }
    }
}
