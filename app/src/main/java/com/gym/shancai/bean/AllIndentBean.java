package com.gym.shancai.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gym on 2017/9/13.
 */

public class AllIndentBean extends ArrayList<AllIndentBean.AllIndentItemBean>{
    public class AllIndentItemBean{


        /**
         * orderid : 1
         * ordernum : 123456789
         * totalprice : 125.0000
         * postprice : 8.0000
         * couponprice : 6.0000
         * orderstatus : 待付款
         * orderdetail : [{"pid":"15","price":"123.0000","num":"4","prodname":"各种菜","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg"},{"pid":"16","price":"100.0000","num":"2","prodname":"干红","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2bdfe00e9.jpg"}]
         */

        private String orderid;
        private String ordernum;
        private String totalprice;
        private String postprice;
        private String couponprice;
        private String orderstatus;
        private List<OrderdetailBean> orderdetail;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getPostprice() {
            return postprice;
        }

        public void setPostprice(String postprice) {
            this.postprice = postprice;
        }

        public String getCouponprice() {
            return couponprice;
        }

        public void setCouponprice(String couponprice) {
            this.couponprice = couponprice;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public List<OrderdetailBean> getOrderdetail() {
            return orderdetail;
        }

        public void setOrderdetail(List<OrderdetailBean> orderdetail) {
            this.orderdetail = orderdetail;
        }

        public class OrderdetailBean {
            /**
             * pid : 15
             * price : 123.0000
             * num : 4
             * prodname : 各种菜
             * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg
             */

            private String pid;
            private String price;
            private String num;
            private String prodname;
            private String content;
            private String thumbnail;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
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
        }
    }
}
