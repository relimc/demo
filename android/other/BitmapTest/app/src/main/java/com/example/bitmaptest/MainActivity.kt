package com.example.bitmaptest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bitmap = BitmapFactory.decodeFile("C:\\Users\\Administrator\\Pictures\\avatar.png")
        findViewById<ImageView>(R.id.iv).setImageBitmap(bitmap)
    }
}