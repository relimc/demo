package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var donwloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("onServiceConnected", name.toString())
            donwloadBinder = service as MyService.DownloadBinder
            donwloadBinder.startDownload()
            donwloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("onServiceDisconnected", name.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startServiceBtn = findViewById<Button>(R.id.startServiceBtn)
        startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        val stopServiceBtn = findViewById<Button>(R.id.stopServiceBtn)
        stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        val bindServiceBtn = findViewById<Button>(R.id.bindServiceBtn)
        bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        val unbindServiceBtn = findViewById<Button>(R.id.unbindServiceBtn)
        unbindServiceBtn.setOnClickListener {
            unbindService(connection)
        }

        val startIntentServiceBtn = findViewById<Button>(R.id.startIntentServiceBtn)
        startIntentServiceBtn.setOnClickListener {
            Log.d("MainActivity", "Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
}