package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 常用清单
 */

public class CommonInventoryBean extends ArrayList<CommonInventoryBean.CommonInventoryInnerBean> {
    public class CommonInventoryInnerBean {


        /**
         * id : 36
         * productid : 15
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg
         * prodname : 各种菜
         * price : 11.00
         * leftnum : 50
         * content : 嘎嘎嘎
         */

        private String id;
        private String productid;
        private String thumbnail;
        private String prodname;
        private String price;
        private int leftnum;
        private String content;
        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getProdname() {
            return prodname;
        }

        public void setProdname(String prodname) {
            this.prodname = prodname;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getLeftnum() {
            return leftnum;
        }

        public void setLeftnum(int leftnum) {
            this.leftnum = leftnum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
