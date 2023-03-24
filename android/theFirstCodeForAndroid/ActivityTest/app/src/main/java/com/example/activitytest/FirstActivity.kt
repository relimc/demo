package com.example.activitytest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

private const val TAG = "FirstActivity"

class FirstActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d(TAG, "Permission is granted.")
                call()
            } else {
                Log.d(TAG, "Permission is not granted.")
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        val firstButton = findViewById<Button>(R.id.button1)
        firstButton.setOnClickListener{
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d(TAG, "checkSelfPermission")
                    call()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
                else -> requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
            }
        }
    }
    private fun call() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }
}