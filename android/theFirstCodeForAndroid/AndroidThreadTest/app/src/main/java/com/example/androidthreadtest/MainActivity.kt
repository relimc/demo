package com.example.androidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val updateText = 1

    // handler 发送消息和处理消息
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> findViewById<TextView>(R.id.textView).text = "Nice to meet you"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val changeTextBtn: Button = findViewById(R.id.changeTextBtn)
        changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()  // Message 在线程间传递消息
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }
    }
}


















