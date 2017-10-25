package com.gym.shancai.http.base;

/**
 * Created by gym on 2017/4/10.
 */

public interface HttpCallBackListener<T> {
        /**
         * 用户操作成功
         * @param statusCode
         * @param bean
         * @param msg
         */
        void callBack(int statusCode, T bean, String msg);

        /**
         * 用户操作失败
         * @param statusCode
         * @param bean
         * @param msg
         * @return 消费错误信息?
         */
        boolean onUserFail(int statusCode, T bean, String msg);

        /**
         * 网络错误回调
         * @param statusCode
         * @param msg
         * @return 消费错误信息?
         */
        boolean onNetError(int statusCode, String msg);


        /**
         * 请求完成回调
         * @param isError 是否失败
         */
        void onComplete(boolean isError);

}
