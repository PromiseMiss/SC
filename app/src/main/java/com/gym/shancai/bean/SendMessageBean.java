package com.gym.shancai.bean;

/**
 * Created by Administrator on 2017/9/5 0005.
 * 发送短信验证码
 */

public class SendMessageBean {

    /**
     * status : 1
     * dete : {"phone":"17302980889"}
     * msg : 验证码发送成功
     */

    private int status;
    private DeteBean dete;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DeteBean getDete() {
        return dete;
    }

    public void setDete(DeteBean dete) {
        this.dete = dete;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DeteBean {
        /**
         * phone : 17302980889
         */

        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
