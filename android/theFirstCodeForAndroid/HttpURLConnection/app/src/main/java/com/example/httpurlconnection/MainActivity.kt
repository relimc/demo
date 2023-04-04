package com.example.httpurlconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var responseText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        responseText = findViewById(R.id.responseText)
        val requestDataBtn = findViewById<Button>(R.id.requestDataBtn)
        requestDataBtn.setOnClickListener {
//            requestByHttpConnection(URL("https://www.toadlive.cn/get_data.json"), object : Callback {
//                override fun success(response: String) {
//                    val jsonArray = JSONArray(response)
//                    for (i in (0 until jsonArray.length())) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        Log.d(TAG, "id is ${jsonObject.getString("id")}")
//                        Log.d(TAG, "name is ${jsonObject.getString("name")}")
//                        Log.d(TAG, "version is ${jsonObject.getString("version")}")
//                    }
//                    val gson = Gson()
//                    val typeOf = object : TypeToken<List<App>>(){}.type
//                    val appList = gson.fromJson<List<App>>(response, typeOf)
//                    val appList = parseJsonArray<List<App>>(response)
//                    Log.d(TAG, "id is ${appList[0].id}")
//                    Log.d(TAG, "id is ${appList[0].name}")
//                    Log.d(TAG, "id is ${appList[0].version}")
//                }
//            })
        }
    }

    private fun <T> parseJsonArray(jsonArray: String): T {
        val gson = Gson()
        val typeOf = object : TypeToken<T>(){}.type
        return gson.fromJson<T>(jsonArray, typeOf)
    }

    private fun requestByHttpConnection(url: URL, callback: Callback) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                var response = StringBuilder()
                connection = url.openConnection() as HttpURLConnection
                connection.apply {
                    requestMethod = "GET"
                    connectTimeout = 8000
                    readTimeout = 8000
                }
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                callback.success(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    interface Callback {
        fun success(response: String)
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            responseText.text = response
        }
    }
}