package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 * <p>
 * 清凉一夏
 */

public class CoolSummerBean  {


    /**
     * picture : http://192.168.1.160/HotelConsortia/Public/Home/Uploads/KingActivity/1.png
     * content : [{"id":"17","prodname":"解百纳","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/5992ae697b824.jpg","price":"109.00","specialprice":"","content":"甘苦","left":109}]
     */

    private String picture;
    private List<ContentBean> content;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 17
         * prodname : 解百纳
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/5992ae697b824.jpg
         * price : 109.00
         * specialprice :
         * content : 甘苦
         * left : 109
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
