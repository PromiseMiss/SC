package com.gym.shancai.http.base;

/**
 * Created by gym on 2017/4/10.
 */

public class BaseBean<T>   {

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    int status;
    String msg;
    T data;
}
