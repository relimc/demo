package com.example.multimoduletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.alibaba.android.arouter.launcher.ARouter

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        findViewById<Button>(R.id.loginBtn).setOnClickListener {
            ARouter.getInstance().build("/login/activity").navigation()
        }
    }
}