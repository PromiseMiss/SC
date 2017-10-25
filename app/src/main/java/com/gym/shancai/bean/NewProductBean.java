package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class NewProductBean extends ArrayList<NewProductBean.NewProductInnerBean> {
    public class NewProductInnerBean {

        /**
         * id : 16
         * prodname : 干红
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2bdfe00e9.jpg
         * price : 109.0000
         * specialprice :
         * left : 100
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String price;
        private String specialprice;
        private int left;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            content = content;
        }

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
