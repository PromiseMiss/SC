package com.gym.shancai.bean;

import com.gym.shancai.http.base.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gym on 2017/9/11.
 */

public class AddressListBean extends ArrayList<AddressListBean.AddressListItemBean> {
    public class AddressListItemBean implements Serializable{

        /**
         * id : 1
         * addressinfo : 辽宁省-沈阳市-沈河区-十一纬路中街
         * receivename : 张三
         * phonenum : 18757843258
         * is_default : 1
         */

        private String id;
        private String addressinfo;
        private String receivename;
        private String phonenum;
        private String is_default;
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddressinfo() {
            return addressinfo;
        }

        public void setAddressinfo(String addressinfo) {
            this.addressinfo = addressinfo;
        }

        public String getReceivename() {
            return receivename;
        }

        public void setReceivename(String receivename) {
            this.receivename = receivename;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }

}
