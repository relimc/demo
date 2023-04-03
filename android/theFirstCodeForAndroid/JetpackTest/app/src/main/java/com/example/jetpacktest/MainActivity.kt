package com.example.jetpacktest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacktest.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        Log.d(TAG, "countReserved is $countReserved")

        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        binding.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        binding.clearBtn.setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this) {
            binding.infoText.text = it.toString()
        }

        binding.infoText.text = countReserved.toString()

        binding.startObservedActivity.setOnClickListener {
            startActivity(Intent(this, ObservedActivity::class.java))
        }

        binding.startUserActivity.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

    }

    private fun refreshCounter() {
        binding.infoText.text = viewModel.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}