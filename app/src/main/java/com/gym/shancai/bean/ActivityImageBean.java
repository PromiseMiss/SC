package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class ActivityImageBean extends ArrayList<ActivityImageBean.ActivityImageInnerBean>{
    public class ActivityImageInnerBean {

        /**
         * id : 17
         * prodname : 解百纳
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/5962f92365328.jpg
         * content : 甘苦
         * price : 109.0000
         * specialprice :
         * salenum : 21
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String content;
        private String price;
        private String specialprice;
        private String salenum;

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

        public String getSpecialprice() {
            return specialprice;
        }

        public void setSpecialprice(String specialprice) {
            this.specialprice = specialprice;
        }

        public String getSalenum() {
            return salenum;
        }

        public void setSalenum(String salenum) {
            this.salenum = salenum;
        }
    }

}
