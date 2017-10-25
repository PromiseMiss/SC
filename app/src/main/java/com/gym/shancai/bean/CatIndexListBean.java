package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/14 0014.
 * 购物车列表
 */

public class CatIndexListBean extends ArrayList<CatIndexListBean.CatIndexListInnerBean> {
    public class CatIndexListInnerBean {


        /**
         * id : 6
         * buyuid : 92
         * goodsprice : 12.00
         * goodsnum : 3
         * goodsid : 16
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2bdfe00e9.jpg
         * prodname : 干红葡萄
         * content : 甘甜
         * status : 2
         */

        private String id;
        private String buyuid;
        private float goodsprice;
        private float goodsnum;
        private String goodsid;
        private String thumbnail;
        private String prodname;
        private String content;
        private String status;
        private boolean isSelect=false;

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

        public String getBuyuid() {
            return buyuid;
        }

        public void setBuyuid(String buyuid) {
            this.buyuid = buyuid;
        }

        public float getGoodsprice() {
            return goodsprice;
        }

        public void setGoodsprice(float goodsprice) {
            this.goodsprice = goodsprice;
        }

        public float getGoodsnum() {
            return goodsnum;
        }

        public void setGoodsnum(float goodsnum) {
            this.goodsnum = goodsnum;
        }

        public String getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(String goodsid) {
            this.goodsid = goodsid;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
