package com.example.workmanagertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.doWorkBtn).setOnClickListener {
//            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
//                .setInitialDelay(5, TimeUnit.MINUTES)
//                .addTag("simple")
//                .build()
//            WorkManager.getInstance(this).enqueue(request)
//            "Toast".showToast()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("person_data", Person())
            startActivity(intent)
        }
    }
}