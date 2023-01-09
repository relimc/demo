package com.example.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.sendRequestBtn).setOnClickListener {
            // sendRequestWithHttpURLConnection()
            // sendRequestWithOkHttp()
            GlobalScope.launch {
                val result = request("https://www.toadlive.cn/blog")
                showResponse(result)
            }
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient() // 获取 OkHttpClient 对象
                // 填充请求需要的信息：比如请求的方法，请求的url，请求头，请求体等
                val request = Request.Builder().url("https://www.toadlive.cn/blog").build()
                val response = client.newCall(request).execute()  // 发起请求
                val responseData = response.body?.string()  // 获取服务端返回的数据
                if (responseData != null) {
                    showResponse(responseData)  // 在应用中更新 UI，将服务端返回的数据展示出来
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendRequestWithHttpURLConnection() {
        thread {  // 网络请求是耗时的 IO 操作，需要在子线程中执行
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()  // StringBuilder 对象，用来存储服务端返回的数据
                val url =  URL("https://www.toadlive.cn/blog")  // 指定要访问的服务端接口
                connection = url.openConnection() as HttpURLConnection  // 发起请求，获取 HttpURLConnection 对象
                connection.requestMethod = "GET"  // 设置 get 请求，从服务端获取数据，这行可不写，默认就是 get
                connection.connectTimeout = 8000  // 给网络请求设置连接超时时间
                connection.readTimeout = 8000  // 给网络请求设置读取超时时间
                val input = connection.inputStream  // 服务端返回的数据作为输入流
                val reader = BufferedReader(InputStreamReader(input))  // 将字节输入流转换为方便操作的字符流
                reader.use {  // use 拓展函数会自动关闭打开的读写资源
                    reader.forEachLine {  // forEachLine 拓展函数会将每一行字符串当作参数传递给其 lambda 表达式参数
                        response.append(it)  // lambda 表达式在这里将每一行字符串都添加到 StringBuilder 对象中
                    }
                }
                showResponse(response.toString()) // 将服务端返回的数据展示出来
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()  // 关闭网络连接
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            findViewById<TextView>(R.id.responseText).text = response
        }
    }
}