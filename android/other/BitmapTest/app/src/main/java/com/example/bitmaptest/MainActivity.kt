package com.example.bitmaptest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlin.math.log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = applicationContext
        val externalFiles = context.getExternalFilesDir(null)
        val externalCache = context.externalCacheDir
        Log.d(TAG, externalCache.toString())
        Log.d(TAG, externalFiles.toString())
    }
}