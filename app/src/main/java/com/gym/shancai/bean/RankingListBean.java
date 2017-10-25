package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 排行榜
 */

public class RankingListBean extends ArrayList<RankingListBean.RankingListInnerBean>{
    public class RankingListInnerBean{

        /**
         * id : 16
         * prodname : 干红
         * thumbnail : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2bdfe00e9.jpg
         * content : 甘甜
         * price : 109.0000
         * salenum : 23
         */

        private String id;
        private String prodname;
        private String thumbnail;
        private String content;
        private String price;
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

        public String getSalenum() {
            return salenum;
        }

        public void setSalenum(String salenum) {
            this.salenum = salenum;
        }
    }
}
