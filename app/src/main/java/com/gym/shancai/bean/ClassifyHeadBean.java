package com.gym.shancai.bean;

import java.util.List;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyHeadBean {

        /**
         * picture : http://192.168.1.160/HotelConsortia/Public/Home/Uploads/type/1.png
         * type : [{"id":"1","parent_id":"0","classname":"蔬菜","path":"0,"},{"id":"3","parent_id":"0","classname":"肉类","path":"0,"},{"id":"6","parent_id":"0","classname":"水果","path":"0,"},{"id":"11","parent_id":"0","classname":"酒水","path":"0,"},{"id":"14","parent_id":"0","classname":"海鲜","path":"0,"}]
         */

        private String picture;
        private List<TypeBean> type;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public static class TypeBean {
            /**
             * id : 1
             * parent_id : 0
             * classname : 蔬菜
             * path : 0,
             */
            private boolean isSelect =false;
            private String id;
            private String parent_id;
            private String classname;
            private String path;

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

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getClassname() {
                return classname;
            }

            public void setClassname(String classname) {
                this.classname = classname;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }


}
