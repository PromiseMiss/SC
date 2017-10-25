package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5 0005.
 * 首页
 */

public class HomeBean {


    /**
     * pictureLoop : [{"pid":"12","picurl":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Loop/5997b1c12c363.png","pcontent":"哒哒哒哒哒哒发发发"},{"pid":"17","picurl":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Loop/5997b1c89dea1.png","pcontent":"阿拉"},{"pid":"14","picurl":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Loop/5997b1cf945a5.png","pcontent":"测试2"}]
     * fontLoop : [{"fontid":"1","fontcontent":"测试1 "},{"fontid":"2","fontcontent":"测试2"},{"fontid":"3","fontcontent":"测试3"},{"fontid":"4","fontcontent":"测试4"},{"fontid":"5","fontcontent":"测试5"},{"fontid":"6","fontcontent":"测试6"},{"fontid":"7","fontcontent":"测试7"},{"fontid":"8","fontcontent":"测试8"},{"fontid":"9","fontcontent":"测试9"},{"fontid":"10","fontcontent":"测试10"},{"fontid":"11","fontcontent":"看看时间格式对不对"}]
     * activity : {"aid":"27","title":"图片尺寸","apicture":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Activity/599e728fa7bd3.png"}
     * recommend : [{"id":"15","prodname":"各种菜","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2e769a9ae.jpg","price":"11.00","specialprice":"8.00","content":"嘎嘎嘎","left":5},{"id":"16","prodname":"干红葡萄","thumbnail":"http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/goods/594e2bdfe00e9.jpg","price":"109.00","specialprice":"","content":"甘甜","left":98}]
     */

    private ActivityBean activity;
    private List<PictureLoopBean> pictureLoop;
    private List<FontLoopBean> fontLoop;
    private List<RecommendBean> recommend;

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
    }

    public List<PictureLoopBean> getPictureLoop() {
        return pictureLoop;
    }

    public void setPictureLoop(List<PictureLoopBean> pictureLoop) {
        this.pictureLoop = pictureLoop;
    }

    public List<FontLoopBean> getFontLoop() {
        return fontLoop;
    }

    public void setFontLoop(List<FontLoopBean> fontLoop) {
        this.fontLoop = fontLoop;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public  class ActivityBean {
        /**
         * aid : 27
         * title : 图片尺寸
         * apicture : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Activity/599e728fa7bd3.png
         */

        private String aid;
        private String title;
        private String apicture;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getApicture() {
            return apicture;
        }

        public void setApicture(String apicture) {
            this.apicture = apicture;
        }
    }

    public  class PictureLoopBean {
        /**
         * pid : 12
         * picurl : http://192.168.1.160/HotelConsortia/Public/Admin/Uploads/Loop/5997b1c12c363.png
         * pcontent : 哒哒哒哒哒哒发发发
         */

        private String pid;
        private String picurl;
        private String pcontent;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPcontent() {
            return pcontent;
        }

        public void setPcontent(String pcontent) {
            this.pcontent = pcontent;
        }
    }

    public  class FontLoopBean {
        /**
         * fontid : 1
         * fontcontent : 测试1
         */

        private String fontid;
        private String fontcontent;

        public String getFontid() {
            return fontid;
        }

        public void setFontid(String fontid) {
            this.fontid = fontid;
        }

        public String getFontcontent() {
            return fontcontent;
        }

        public void setFontcontent(String fontcontent) {
            this.fontcontent = fontcontent;
        }
    }

    public  class RecommendBean {
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
