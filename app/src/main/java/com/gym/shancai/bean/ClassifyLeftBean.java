package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyLeftBean extends ArrayList<ClassifyLeftBean.ClassifyLeftInnerBean>{
    public class ClassifyLeftInnerBean
    {
        /**
         * id : 2
         * parent_id : 1
         * classname : 白菜
         * path : 0,1,
         */

        private String id;
        private String parent_id;
        private String classname;
        private String path;
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
