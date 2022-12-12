package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class FirstActivity : AppCompatActivity() {

    private val requestDataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val data = it.data?.getStringExtra("data")
            if (data != null) {
                Log.d("FirstActivity", data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val firstButton = findViewById<Button>(R.id.button1)
        firstButton.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            requestDataLauncher.launch(intent)
        }
    }
}