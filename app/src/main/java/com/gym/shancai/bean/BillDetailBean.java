package com.gym.shancai.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 账单明细
 */

public class BillDetailBean extends ArrayList<BillDetailBean.BillDetailInnerBean> {
    public class BillDetailInnerBean {

        /**
         * month : 2017年08月
         * content : [{"ordercreatedate":"1502697769","totalprice":"125.00"},{"ordercreatedate":"1502697769","totalprice":"125.00"}]
         */

        private String month;
        private List<ContentBean> content;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public class ContentBean {
            /**
             * ordercreatedate : 1502697769
             * totalprice : 125.00
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
}