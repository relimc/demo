package com.example.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ObservedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observed)
        lifecycle.addObserver(MyObserver(lifecycle))
    }
}