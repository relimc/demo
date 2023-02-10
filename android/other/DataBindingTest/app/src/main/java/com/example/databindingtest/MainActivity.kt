package com.example.databindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databindingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv.text = "666"
    }
}