package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by gym on 2017/9/12.
 */

public class ProductDetailBean {

    /**
     * loop : [{"picture":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/59645cacdd9ce.jpg"},{"picture":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/595068cb3f491.jpg"},{"picture":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e0c2d88c82.jpg"}]
     * imagetext : {"imagetext":""}
     * goodsdetail : {"pid":"15","price":"11.00","prodname":"各种菜","content":"嘎嘎嘎","format":"斤","specialprice":null}
     * serverphone : 18745678654
     */

    private ImagetextBean imagetext;
    private GoodsdetailBean goodsdetail;
    private String serverphone;
    private List<LoopBean> loop;

    public ImagetextBean getImagetext() {
        return imagetext;
    }

    public void setImagetext(ImagetextBean imagetext) {
        this.imagetext = imagetext;
    }

    public GoodsdetailBean getGoodsdetail() {
        return goodsdetail;
    }

    public void setGoodsdetail(GoodsdetailBean goodsdetail) {
        this.goodsdetail = goodsdetail;
    }

    public String getServerphone() {
        return serverphone;
    }

    public void setServerphone(String serverphone) {
        this.serverphone = serverphone;
    }

    public List<LoopBean> getLoop() {
        return loop;
    }

    public void setLoop(List<LoopBean> loop) {
        this.loop = loop;
    }

    public static class ImagetextBean {
        /**
         * imagetext :
         */

        private String imagetext;

        public String getImagetext() {
            return imagetext;
        }

        public void setImagetext(String imagetext) {
            this.imagetext = imagetext;
        }
    }

    public static class GoodsdetailBean {
        /**
         * pid : 15
         * price : 11.00
         * prodname : 各种菜
         * content : 嘎嘎嘎
         * format : 斤
         * specialprice : null
         */

        private String pid;
        private String price;
        private String prodname;
        private String content;
        private String format;
        private Object specialprice;

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

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Object getSpecialprice() {
            return specialprice;
        }

        public void setSpecialprice(Object specialprice) {
            this.specialprice = specialprice;
        }
    }

    public static class LoopBean {
        /**
         * picture : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/59645cacdd9ce.jpg
         */

        private String picture;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
