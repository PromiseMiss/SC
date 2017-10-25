package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 搜索
 */

public class SearchBean extends ArrayList<SearchBean.SearchInnerBean>{
    public class SearchInnerBean {

        /**
         * id : 20
         * prodname : 测试特卖
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/598a9c2548f61.jpg
         * price : 223.0000
         * specialprice :
         * left : 123
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String price;
        private String specialprice;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

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

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
    }
}
