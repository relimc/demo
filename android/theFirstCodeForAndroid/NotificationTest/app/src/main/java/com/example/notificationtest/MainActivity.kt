package com.example.notificationtest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
        val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel)
        manager.createNotificationChannel(channel2)
        findViewById<Button>(R.id.sendNotice).setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val flags = FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
            val pi = PendingIntent.getActivity(this, 0, intent, flags)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }
}