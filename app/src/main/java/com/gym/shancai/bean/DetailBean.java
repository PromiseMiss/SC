package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class DetailBean {

    /**
     * balance : 1700.00
     * tradedetail : [{"paytype":"4","comtime":"1501652343","givemoney":"5.00"},{"paytype":"1","comtime":"1503025344","givemoney":"-100.00"},{"paytype":"5","comtime":"1503046239","givemoney":"-20.00"},{"paytype":"1","comtime":"1503046281","givemoney":"-100.00"}]
     */

    private String balance;
    private List<TradedetailBean> tradedetail;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<TradedetailBean> getTradedetail() {
        return tradedetail;
    }

    public void setTradedetail(List<TradedetailBean> tradedetail) {
        this.tradedetail = tradedetail;
    }

    public static class TradedetailBean {
        /**
         * paytype : 4
         * comtime : 1501652343
         * givemoney : 5.00
         */

        private String paytype;
        private String comtime;
        private String givemoney;

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getComtime() {
            return comtime;
        }

        public void setComtime(String comtime) {
            this.comtime = comtime;
        }

        public String getGivemoney() {
            return givemoney;
        }

        public void setGivemoney(String givemoney) {
            this.givemoney = givemoney;
        }
    }
}
