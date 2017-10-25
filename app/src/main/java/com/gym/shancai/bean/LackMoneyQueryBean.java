package com.gym.shancai.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 差款查询
 */

public class LackMoneyQueryBean extends ArrayList<LackMoneyQueryBean.LackMoneyInnerBean> {

    public class LackMoneyInnerBean {


        /**
         * month : 2017年07月
         * content : [{"id":"22","paytype":"5","acttime":"1499745661","iscomplete":"1","premoney":"120.00","reallymoney":"122.00","givemoney":"-2.00"},{"id":"24","paytype":"5","acttime":"1499745661","iscomplete":"2","premoney":"123.00","reallymoney":"128.00","givemoney":"-5.00"}]
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
             * id : 22
             * paytype : 5
             * acttime : 1499745661
             * iscomplete : 1
             * premoney : 120.00
             * reallymoney : 122.00
             * givemoney : -2.00
             */

            private String id;
            private String paytype;
            private String acttime;
            private String iscomplete;
            private String premoney;
            private String reallymoney;
            private String givemoney;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPaytype() {
                return paytype;
            }

            public void setPaytype(String paytype) {
                this.paytype = paytype;
            }

            public String getActtime() {
                return acttime;
            }

            public void setActtime(String acttime) {
                this.acttime = acttime;
            }

            public String getIscomplete() {
                return iscomplete;
            }

            public void setIscomplete(String iscomplete) {
                this.iscomplete = iscomplete;
            }

            public String getPremoney() {
                return premoney;
            }

            public void setPremoney(String premoney) {
                this.premoney = premoney;
            }

            public String getReallymoney() {
                return reallymoney;
            }

            public void setReallymoney(String reallymoney) {
                this.reallymoney = reallymoney;
            }

            public String getGivemoney() {
                return givemoney;
            }

            public void setGivemoney(String givemoney) {
                this.givemoney = givemoney;
            }
        }
    }
}
