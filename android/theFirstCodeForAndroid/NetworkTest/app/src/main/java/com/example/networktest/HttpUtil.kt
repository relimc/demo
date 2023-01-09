package com.example.networktest

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {
    fun sendHttpRequest(address: String, listener: HttpCallbackListener) {  // 设置 listener 参数用来监听网络请求
        thread { // 由于网络请求是耗时操作，必须在子线程中执行，否则可能出现 sendHttpRequest 方法执行结束后，服务端的数据还没返回
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                listener.onFinish(response.toString())  // 子线程中不能使用 return 来返回 response，这里将 response 当作参数传递给 onFinish 回调
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onError(e)  // 请求异常时，将异常传递给 onError 回调
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
        // enqueue 方法，会开启一个子线程，在子线程完成网络请求操作，响应的结果会作为参数传递给 callback 对象的方法
        // callback 是个接口，其有 onResponse 和 onFailure 两个抽象方法，当请求成功时，会将响应的结果传递给 onResponse，当请求失败时，会将响应的结果传递给 onFailure 方法
    }
}