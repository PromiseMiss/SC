package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 今日特价
 */

public class DayDealsBean extends ArrayList<DayDealsBean.DayDealsInnerBean>{
    public class DayDealsInnerBean{

        /**
         * id : 15
         * prodname : 各种菜
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg
         * price : 11.00
         * specialprice : 8.00
         * content : 嘎嘎嘎
         * left : 5
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String price;
        private String specialprice;
        private String content;
        private int left;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getSpecialprice() {
            return specialprice;
        }

        public void setSpecialprice(String specialprice) {
            this.specialprice = specialprice;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
    }
}
