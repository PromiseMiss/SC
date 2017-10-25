package com.gym.shancai.http.base;

/**
 * 创建:gym
 * 日期:2017-01-17.
 * 说明:
 * 备注:
 */

public  class ResponseBean {
    String body;
    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public ResponseBean setCode(int code) {
        this.code = code;
        return ResponseBean.this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseBean setMsg(String msg) {
        this.msg = msg;
        return ResponseBean.this;
    }

    public String getBody() {
        return body;
    }

    public ResponseBean setBody(String body) {
        this.body = body;
        return ResponseBean.this;
    }


}