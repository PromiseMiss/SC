package com.gym.shancai.bean;

/**
 * Created by gym on 2017/9/8.
 */

public class MyBean {

    /**
     * username :
     * avatar : http://192.168.1.160__PUBLIC__/Admin/Uploads/avatar/default.jpg
     * status : 2   1:饭店用户 2：普通用户
     */

    private String username;
    private String avatar;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
