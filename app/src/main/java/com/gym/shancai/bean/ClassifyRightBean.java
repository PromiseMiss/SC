package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyRightBean extends ArrayList<ClassifyRightBean.ClassifyRightInnerBean>{
    public class ClassifyRightInnerBean{

        /**
         * id : 17
         * prodname : 解百纳
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/5992ae697b824.jpg
         * content : 甘苦
         * price : 109.00
         * left : 92
         * specialprice :
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String content;
        private String price;
        private int left;
        private String specialprice;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public String getSpecialprice() {
            return specialprice;
        }

        public void setSpecialprice(String specialprice) {
            this.specialprice = specialprice;
        }
    }
}
