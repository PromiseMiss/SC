package com.gym.shancai.utils.den

import android.content.Intent
import android.os.Handler
import android.text.TextUtils

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.gym.shancai.APP
import com.gym.shancai.activity.LoginActivity
import com.gym.shancai.activity.MainActivity
import com.gym.shancai.http.base.ResponseBean
import com.gym.shancai.http.base.ServerUrl
import com.gym.shancai.utils.*

import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.*
import java.util.Map


/**
 * Created by gym on 2017/4/24.
 */

class PostCall {
    interface PostResponse<T> {
        fun onSuccess(statusCode: Int, responseBeanBody: ResponseBean?, responseBean: T, msg: String)
        fun onFailure(statusCode: Int, responseBeanBody: ResponseBean?, msg: String)

    }

    private inner class DeBaseBean constructor(var status:Int,var msg: String,var data : String?)



    companion object {
        val handler = Handler()
        val JSON = MediaType.parse("application/json; charset=utf-8")
        private val CONNECT_TIMEOUT: Long = 10
        internal var client = OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

        fun <T> post(url: String, params: HashMap<String, String>?, response: PostResponse<T>?,
                     clazz: Class<T>) {
            val request: Request
            var logString = ""
//            if (params != null && !params.isEmpty()) {
                val bodyBuilder = FormBody.Builder()
                //            RequestBody body = RequestBody.create(JSON, GsonUtils.getInstance().toJson());
                val iterator = params!!.entries.iterator()

                while (iterator.hasNext()) {
                    val entry = iterator.next() as Map.Entry<*, *>
                    val key = entry.key as String
                    var `val` = entry.value as String?
                    logString += "$key:$`val`&"
                    //                Logger.e("~~~~~~~~","~~~~~~"+"key"+key+"~~~val:"+val);
                    if (`val`==null){
                        `val`=""
                    }
                    bodyBuilder.add(key, `val`)
                }
                    if (url != ServerUrl.getInstance().login) {

                        if (BaseSharedPreferences.getInstance().loginInfo == null || BaseSharedPreferences.getInstance().loginInfo.phonetoken == null) {
                            bodyBuilder.add("phonetoken", "")
                            logString += "phonetoken" + ":" + "" + "&"
                        } else {
                            bodyBuilder.add("phonetoken", BaseSharedPreferences.getInstance().loginInfo.phonetoken)
                            logString += "phonetoken" + ":" + BaseSharedPreferences.getInstance().loginInfo.phonetoken + "&"
                        }
                    }


                val formBody = bodyBuilder.build()
                request = Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build()

//            } else {
//                request = Request.Builder()
//                        .url(url)
//                        .build()
//            }
            Logger.e("请求参数", "url:$url    参数:$logString")

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    if (response != null) {
                        handler.post {
                            if (SocketTimeoutException::class.java == e.cause)
                            //如果超时
                            {
                                //超时错误
                                response.onFailure(-1, null, "网络连接超时")
                            } else {
                                response.onFailure(-1, null, "连接失败")
                            }
                        }
                    }
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, okResponse: Response) {


                    if (okResponse.code() == 404) {
                        if (response != null) {
                            handler.post { response.onFailure(-1, null, "404:" + okResponse.message()) }
                        }
                        return
                    }
                    //                Logger.e("地址状态 ", "address satus:"+response.code());
                    if (response != null) {
                        var bodySting: String? = null
                        if (TextUtils.isEmpty(okResponse.body().toString())) {
                            handler.post { response.onFailure(-1, null, "返回的数据空了" + okResponse.message()) }
                            return
                        }
                        try {
                            bodySting = okResponse.body()!!.string()
                            //                        bodySting = URLDecoder.decode(bodySting, "utf-8");
                            bodySting = bodySting!!.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25")

                            //                        bodySting = URLDecoder.decode(bodySting, "UTF-8");

                            //                        Logger.d("AsyncHttpClient.post()------", url + "?" + params + "    response:" + str);

                        } catch (e: Exception) {
                            val finalBodySting2 = bodySting
                            handler.post { response.onFailure(-1, null, "将字符转换UTF-8异常" + finalBodySting2!!) }
                            return
                        }
                        var g = Gson()
                        var dbb: DeBaseBean?
                        try {
                        dbb = g.fromJson(bodySting, DeBaseBean::class.java)

                        }
                        catch (e: Exception) {
                            val finalBodySting = bodySting
                            handler.post {
                                Logger.e("~~~", e.message+"解析异常" + finalBodySting+"")
                                response.onFailure(-1, null, "Bean解析异常")
                            }
                            return
                        }

                        if (dbb == null) {
                            handler.post { response.onFailure(-1, null, "服务器异常，返回的值空了") }

                            return
                        }
                        try {
                            dbb.data = AesUtil2.decode(dbb.data)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
//                        dbb.data = dbb.data?.replace("[\"\"]", "[]")
                        Logger.e("获取的数据---", "res---url:" + url + "---msg:" + dbb.msg + "---状态:" + dbb.status + "data:" + dbb.data.toString())
                       val bean = g.fromJson(dbb.data.toString() , clazz)

                        val status = dbb.status
                        val msg = dbb.msg
                        if (status == -7) {
                            //异地登录
                            handler.post {
                                // TODO Auto-generated method stub
                                Logger.e("异地登陆~~~~")
//                                if (MainActivity.getInstence() != null)
//                                    MainActivity.getInstence().finish()
                                SharedPreferencesSetting.instance.clearnLoginInfo();
                                val intent = Intent(APP.getInstance(), LoginActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                intent.putExtra("otherlog", true)
                                APP.getInstance().startActivity(intent)
                            }
                            return
                        }

                        handler.post { response.onSuccess(status, null, bean, msg) }

                    }

                }


            })
        }
    }
}
