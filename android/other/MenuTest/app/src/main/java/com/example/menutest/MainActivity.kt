package com.example.menutest

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.menutest.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        Log.d(TAG, "RequestPermissions")
        permissions.entries.forEach {
            Log.d(TAG, it.key)
            Log.d(TAG, it.value.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            launcher.launch(arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.NFC,
                Manifest.permission.ACCESS_FINE_LOCATION
            ))
        }
    }
}