package com.gym.shancai.bean;

import java.util.ArrayList;

/**
 * Created by gym on 2017/9/8.
 */

public class RegisterSelectAddressBean extends ArrayList<RegisterSelectAddressBean.RegisterSelectAddressItemBean>{
    public class RegisterSelectAddressItemBean{

        /**
         * id : 1
         * pid : 0
         * name : 北京
         */

        private String id;
        private String pid;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
